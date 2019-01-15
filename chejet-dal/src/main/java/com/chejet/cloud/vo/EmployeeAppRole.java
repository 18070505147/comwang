package com.chejet.cloud.vo;

import java.util.List;

/**
 * @Description
 * @Date 2018/12/27 10:10
 * @Version 1.0
 */
public class EmployeeAppRole {
    /**
     * 应用ID
     */
    private Long appId;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 员工ID
     */
    private Long employeeId;
    /**
     * 应用下角色列表
     */
    private List<RoleItem> roleList;
    /**
     * 员工已选角色列表
     */
    private List<RoleItem> checkedRole;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public List<RoleItem> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<RoleItem> roleList) {
        this.roleList = roleList;
    }

    public List<RoleItem> getCheckedRole() {
        return checkedRole;
    }

    public void setCheckedRole(List<RoleItem> checkedRole) {
        this.checkedRole = checkedRole;
    }
}
