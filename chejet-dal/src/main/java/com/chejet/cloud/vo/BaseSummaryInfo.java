package com.chejet.cloud.vo;

/**
 * @Description
 * @Date 2018/12/11 16:36
 * @Version 1.0
 */
public class BaseSummaryInfo {
    /**
     * 企业数
     */
    private Long companyNum;

    /**
     * 应用数
     * 企业管理员-管理应用数
     * 租户管理员-上线应用数
     */
    private Long appNum;


    public Long getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(Long companyNum) {
        this.companyNum = companyNum;
    }

    public Long getAppNum() {
        return appNum;
    }

    public void setAppNum(Long appNum) {
        this.appNum = appNum;
    }
}
