package org.zenoda.ztrader.data.domain;

public class DataCategory {
    private String dataCategoryCode;

    private String dataCategoryName;

    private Long dataRows;

    private Long dataUpdatedAt;

    private Long dataUpdatedBy;

    public String getDataCategoryCode() {
        return dataCategoryCode;
    }

    public void setDataCategoryCode(String dataCategoryCode) {
        this.dataCategoryCode = dataCategoryCode;
    }

    public String getDataCategoryName() {
        return dataCategoryName;
    }

    public void setDataCategoryName(String dataCategoryName) {
        this.dataCategoryName = dataCategoryName;
    }

    public Long getDataRows() {
        return dataRows;
    }

    public void setDataRows(Long dataRows) {
        this.dataRows = dataRows;
    }

    public Long getDataUpdatedAt() {
        return dataUpdatedAt;
    }

    public void setDataUpdatedAt(Long dataUpdatedAt) {
        this.dataUpdatedAt = dataUpdatedAt;
    }

    public Long getDataUpdatedBy() {
        return dataUpdatedBy;
    }

    public void setDataUpdatedBy(Long dataUpdatedBy) {
        this.dataUpdatedBy = dataUpdatedBy;
    }
}