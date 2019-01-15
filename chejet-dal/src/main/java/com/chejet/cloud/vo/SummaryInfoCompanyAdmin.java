package com.chejet.cloud.vo;

/**
 * @Description
 * @Date 2018/12/11 16:40
 * @Version 1.0
 */
public class SummaryInfoCompanyAdmin extends BaseSummaryInfo {
    /**
     * 员工数
     */
    private Long employeeNum;

    public Long getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(Long employeeNum) {
        this.employeeNum = employeeNum;
    }
}
