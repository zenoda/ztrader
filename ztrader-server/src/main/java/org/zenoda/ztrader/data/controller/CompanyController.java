package org.zenoda.ztrader.data.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.zenoda.ztrader.common.DefaultController;
import org.zenoda.ztrader.data.domain.Company;
import org.zenoda.ztrader.data.domain.DataCategory;
import org.zenoda.ztrader.data.mapper.CompanyMapper;
import org.zenoda.ztrader.data.service.CompanyService;

@RestController
@RequestMapping("/data/company")
public class CompanyController extends DefaultController<CompanyService, Company, CompanyMapper> {
    public CompanyController(CompanyService service) {
        super(service);
    }

    @PostMapping(value = "/all/download")
    public SseEmitter downloadCompanyList(@RequestBody DataCategory category) {
        return getDefaultService().downloadCompanyList(category);
    }
}
