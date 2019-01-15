package com.chejet.cloud.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.AssignID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * rel_role_data_permission
 * @author 
 */
public class RelRoleDataPermission implements Serializable {
    /**
     * 编号
     */
    @AssignID()
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 角色信息授权
     */
    private Long roleId;

    /**
     * 敏感信息id
     */
    private Long sentitiveId;

    /**
     * 功能分类 关联功能表
     */
    private String functionType;

    /**
     * 修改 false-不可修改，true-可修改
     */
    private Boolean modifiedFlag;

    /**
     * 查看 false-不可查看，true-可查看
     */
    private Boolean viewFlag;

    /**
     * 创建时间
     */
    @JsonIgnore
    private Date ctime;

    /**
     * 更新时间
     */
    @JsonIgnore
    private Date mtime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getSentitiveId() {
        return sentitiveId;
    }

    public void setSentitiveId(Long sentitiveId) {
        this.sentitiveId = sentitiveId;
    }

    public String getFunctionType() {
        return functionType;
    }

    public void setFunctionType(String functionType) {
        this.functionType = functionType;
    }

    public Boolean getModifiedFlag() {
        return modifiedFlag;
    }

    public void setModifiedFlag(Boolean modifiedFlag) {
        this.modifiedFlag = modifiedFlag;
    }

    public Boolean getViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(Boolean viewFlag) {
        this.viewFlag = viewFlag;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RelRoleDataPermission other = (RelRoleDataPermission) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getSentitiveId() == null ? other.getSentitiveId() == null : this.getSentitiveId().equals(other.getSentitiveId()))
            && (this.getFunctionType() == null ? other.getFunctionType() == null : this.getFunctionType().equals(other.getFunctionType()))
            && (this.getModifiedFlag() == null ? other.getModifiedFlag() == null : this.getModifiedFlag().equals(other.getModifiedFlag()))
            && (this.getViewFlag() == null ? other.getViewFlag() == null : this.getViewFlag().equals(other.getViewFlag()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getSentitiveId() == null) ? 0 : getSentitiveId().hashCode());
        result = prime * result + ((getFunctionType() == null) ? 0 : getFunctionType().hashCode());
        result = prime * result + ((getModifiedFlag() == null) ? 0 : getModifiedFlag().hashCode());
        result = prime * result + ((getViewFlag() == null) ? 0 : getViewFlag().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getMtime() == null) ? 0 : getMtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roleId=").append(roleId);
        sb.append(", sentitiveId=").append(sentitiveId);
        sb.append(", functionType=").append(functionType);
        sb.append(", modifiedFlag=").append(modifiedFlag);
        sb.append(", viewFlag=").append(viewFlag);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}