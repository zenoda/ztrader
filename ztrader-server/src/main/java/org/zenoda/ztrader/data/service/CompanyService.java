package org.zenoda.ztrader.data.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.zenoda.ztrader.auth.web.SessionContext;
import org.zenoda.ztrader.common.DefaultService;
import org.zenoda.ztrader.data.domain.Company;
import org.zenoda.ztrader.data.domain.DataCategory;
import org.zenoda.ztrader.data.mapper.CompanyMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class CompanyService extends DefaultService<Company, CompanyMapper> {
    private RestTemplate restTemplate=new RestTemplate();

    public CompanyService(SessionContext sessionContext, CompanyMapper repository) {
        super(sessionContext, repository);
    }

    public SseEmitter downloadCompanyList(DataCategory category) {
        SseEmitter sseEmitter = new SseEmitter();
        CompletableFuture.runAsync(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    List<Company> companies = readCompanyList(i + 1, 100);
                    if (companies != null && !companies.isEmpty()) {
                        companies.forEach(this::save);
                        sseEmitter.send((i + 1) * 10);
                    } else {
                        sseEmitter.complete();
                        break;
                    }
                }
            } catch (IOException e) {
                sseEmitter.completeWithError(e);
            }
        });
        return sseEmitter;
    }

    private List<Company> readCompanyList(int pageNum, int pageSize) {
        String url = "https://push2.eastmoney.com/api/qt/clist/get?np=1&fltt=1&invt=2"
                + "&fs=m:1+t:2+f:!2,m:1+t:23+f:!2,m:0+t:6+f:!2,m:0+t:80+f:!2"
                + "&fields=f12,f13,f14,f9,f23&fid=f3&pn=" + pageNum + "&pz=" + pageSize + "&po=1&dect=1";
        Map result = restTemplate.getForObject(url, Map.class);
        Map data = (Map) result.get("data");
        if (data == null) {
            return null;
        }
        List<Map> diff = (List<Map>) data.get("diff");
        if (diff == null) {
            return null;
        }
        return diff.stream().map(item -> {
            Company company = new Company();
            company.setCompanyCode((String) item.get("f13"));
            company.setCompanyName((String) item.get("f14"));
            company.setMarket((Integer) item.get("f12"));
            company.setPe((Long) item.get("f9"));
            company.setTotalCap((Long) item.get("f23"));
            return company;
        }).toList();
    }
}
