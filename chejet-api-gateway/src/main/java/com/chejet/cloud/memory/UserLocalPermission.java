package com.chejet.cloud.memory;

import com.chejet.cloud.rpc.RpcPermission;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author Neo.fang
 * @creatTime 2018/11/12 0012
 */
public class UserLocalPermission implements Serializable {

    private static final long serialVersionUID = 7744061178030182892L;

    // 用户权限对象列表(All)
    private List<RpcPermission> permissionList;

    // 用户权限配置的的url集合，用于匹配path
    private Set<String> permissionSet;

    // 用户没有的权限，方便前端去隐藏相应操作按钮
    private String noPermissions;


    public List<RpcPermission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<RpcPermission> permissionList) {
        this.permissionList = permissionList;
    }

    public Set<String> getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Set<String> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public String getNoPermissions() {
        return noPermissions;
    }

    public void setNoPermissions(String noPermissions) {
        this.noPermissions = noPermissions;
    }
}
