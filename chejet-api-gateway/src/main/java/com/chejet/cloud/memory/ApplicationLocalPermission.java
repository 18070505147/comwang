package com.chejet.cloud.memory;

import com.chejet.cloud.rpc.RpcPermission;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author Neo.fang
 * @creatTime 2018/11/12 0012
 *
 * 存储全部的权限设置, 反向排除判断时可用
 */
public class ApplicationLocalPermission implements Serializable {

    private static final long serialVersionUID = 7744011178036882892L;

    // 应用所有权限对象
    private List<RpcPermission> applicationRpcPermissionList = null;
    // 应用所有权限URL
    private Set<String> applicationPermissionSet = null;

    public List<RpcPermission> getApplicationRpcPermissionList() {
        return applicationRpcPermissionList;
    }

    public void setApplicationRpcPermissionList(List<RpcPermission> applicationRpcPermissionList) {
        this.applicationRpcPermissionList = applicationRpcPermissionList;
    }

    public Set<String> getApplicationPermissionSet() {
        return applicationPermissionSet;
    }

    public void setApplicationPermissionSet(Set<String> applicationPermissionSet) {
        this.applicationPermissionSet = applicationPermissionSet;
    }
}
