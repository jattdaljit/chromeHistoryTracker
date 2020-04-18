package com.epam.wfh.manager.model;

public class ChromeHistoryTimes {
    Integer productiveTime;
    Integer nonProductiveTime;

    public Integer getProductiveTime() {
        return productiveTime;
    }

    public void setProductiveTime(Integer productiveTime) {
        this.productiveTime = productiveTime;
    }

    public Integer getNonProductiveTime() {
        return nonProductiveTime;
    }

    public void setNonProductiveTime(Integer nonProductiveTime) {
        this.nonProductiveTime = nonProductiveTime;
    }

    @Override
    public String toString() {
        return "ChromeTimes{" +
                "productiveTime=" + productiveTime +
                ", nonProductiveTime=" + nonProductiveTime +
                '}';
    }
}
