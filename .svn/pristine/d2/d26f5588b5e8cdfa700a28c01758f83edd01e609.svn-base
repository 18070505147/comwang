package com.chejet.cloud.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.Table;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/18 15:09
 */
@Table(name = "rel_role_point_permission")
public class FunctionPermissionPointVO {
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long permissionPointId;
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long roleId;
    private Boolean enableFlag;
    private Boolean closeFlag;

    public Boolean getCloseFlag() {
        return closeFlag;
    }

    public void setCloseFlag(Boolean closeFlag) {
        this.closeFlag = closeFlag;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionPointId() {
        return permissionPointId;
    }

    public void setPermissionPointId(Long permissionPointId) {
        this.permissionPointId = permissionPointId;
    }

    public Boolean getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Boolean enableFlag) {
        this.enableFlag = enableFlag;
    }

    @Override
    public String toString() {
        return "FunctionPermissionPointVO{" +
                "permissionPointId=" + permissionPointId +
                ", roleId=" + roleId +
                ", enableFlag=" + enableFlag +
                '}';
    }
}
