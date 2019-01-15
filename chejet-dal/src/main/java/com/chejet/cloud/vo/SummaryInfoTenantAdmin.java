package com.chejet.cloud.vo;

/**
 * @Description
 * @Date 2018/12/11 16:42
 * @Version 1.0
 */
public class SummaryInfoTenantAdmin extends BaseSummaryInfo {
    /**
     * 应用用户数
     */
    private Long appUserNum;

    public Long getAppUserNum() {
        return appUserNum;
    }

    public void setAppUserNum(Long appUserNum) {
        this.appUserNum = appUserNum;
    }
}
