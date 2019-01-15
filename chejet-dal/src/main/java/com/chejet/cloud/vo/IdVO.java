package com.chejet.cloud.vo;

import java.util.List;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/17 20:51
 */
public class IdVO {
    private Long tenantId;
    private Long companyId;
    private Long appId;
    private Long employeeId;
    private Long userId;
    private Long roleId;
    private String condition;
    private Integer pageNo;
    private Integer pageSize;
    private Long versionId;
    private List<Long> employeeIdList;

    public List<Long> getEmployeeIdList() {
        return employeeIdList;
    }

    public void setEmployeeIdList(List<Long> employeeIdList) {
        this.employeeIdList = employeeIdList;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
