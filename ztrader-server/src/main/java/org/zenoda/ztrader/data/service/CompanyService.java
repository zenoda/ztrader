package org.zenoda.ztrader.data.service;

import cn.hutool.core.map.MapUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.zenoda.ztrader.auth.web.SessionContext;
import org.zenoda.ztrader.common.DefaultService;
import org.zenoda.ztrader.common.SseChunk;
import org.zenoda.ztrader.data.domain.Company;
import org.zenoda.ztrader.data.domain.DataCategory;
import org.zenoda.ztrader.data.domain.TuShareRequest;
import org.zenoda.ztrader.data.mapper.CompanyMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class CompanyService extends DefaultService<Company, CompanyMapper> {
    private final RestTemplate restTemplate = new RestTemplate();
    private final CompanyService self;

    public CompanyService(SessionContext sessionContext, CompanyMapper repository, @Lazy CompanyService self) {
        super(sessionContext, repository);
        this.self = self;
    }

    public SseEmitter downloadCompanyList(DataCategory category) {
        SseEmitter sseEmitter = new SseEmitter();
        HttpSession session = getSessionContext().getSession();
        CompletableFuture.runAsync(() -> {
            getSessionContext().setSession(session);
            List<Company> companies = readCompanyList();
            self.saveCompanyList(companies, sseEmitter);
        });
        return sseEmitter;
    }

    private List<Company> readCompanyList() {
        String url = "http://api.tushare.pro";
        TuShareRequest request = TuShareRequest.builder()
                .token("34bd4f9e25a0c68247dfca233e12ee65631f54259c5f31a4d482f606")
                .apiName("stock_basic")
                .fields("ts_code,name,area,industry,cnspell,market,list_date")
                .params(MapUtil.builder()
                        .put("list_status", "L")
                        .build())
                .build();
        var resp = restTemplate.postForObject(url, request, Map.class);
        if (resp == null) {
            return null;
        }
        Map<String, List<List<String>>> data = (Map<String, List<List<String>>>) resp.get("data");
        if (data == null) {
            return null;
        }
        List<List<String>> items = data.get("items");
        if (items == null) {
            return null;
        }
        List<Company> companies = new ArrayList<>();
        for (List<String> item : items) {
            Company company = new Company();
            company.setCode(item.get(0));
            company.setName(item.get(1));
            company.setArea(item.get(2));
            company.setIndustry(item.get(3));
            company.setCnSpell(item.get(4));
            company.setMarket(item.get(5));
            company.setListDate(item.get(6));
            companies.add(company);
        }
        return companies;
    }

    @Transactional
    protected void saveCompanyList(List<Company> companies, SseEmitter sseEmitter) {
        try {
            if (companies == null || companies.isEmpty()) {
                sseEmitter.complete();
                return;
            }
            getDefaultMapper().deleteAll();
            for (int i = 0; i < companies.size(); i++) {
                Company company = companies.get(i);
                this.save(company);
                if (i % 20 == 0) {
                    sseEmitter.send(SseChunk.builder()
                            .type("progress")
                            .content((i + 1) * 100 / companies.size())
                            .build());
                }
            }
            sseEmitter.complete();
        } catch (Exception e) {
            sseEmitter.completeWithError(e);
        }
    }
}
