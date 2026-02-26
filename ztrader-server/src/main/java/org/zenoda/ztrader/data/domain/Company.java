package org.zenoda.ztrader.data.domain;

import org.zenoda.ztrader.common.DefaultMutableEntity;

public class Company extends DefaultMutableEntity {
    private String code;
    private String name;
    private String area;
    private String industry;
    private String cnSpell;
    private String market;
    private String listDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCnSpell() {
        return cnSpell;
    }

    public void setCnSpell(String cnSpell) {
        this.cnSpell = cnSpell;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }
}