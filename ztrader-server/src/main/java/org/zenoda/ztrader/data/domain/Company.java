package org.zenoda.ztrader.data.domain;

import org.zenoda.ztrader.common.DefaultMutableEntity;

public class Company extends DefaultMutableEntity {
    private String companyCode;

    private String companyName;

    private Integer market;

    private Long pe;

    private Long pb;

    private Long totalCap;

    private Long floatingCap;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getMarket() {
        return market;
    }

    public void setMarket(Integer market) {
        this.market = market;
    }

    public Long getPe() {
        return pe;
    }

    public void setPe(Long pe) {
        this.pe = pe;
    }

    public Long getPb() {
        return pb;
    }

    public void setPb(Long pb) {
        this.pb = pb;
    }

    public Long getTotalCap() {
        return totalCap;
    }

    public void setTotalCap(Long totalCap) {
        this.totalCap = totalCap;
    }

    public Long getFloatingCap() {
        return floatingCap;
    }

    public void setFloatingCap(Long floatingCap) {
        this.floatingCap = floatingCap;
    }
}