package com.chejet.cloud.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.chejet.cloud.vo.FunctionPermissionPointVO;
import com.chejet.cloud.vo.SensitiveDataVO;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.annotatoin.UpdateIgnore;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * sys_group_app_role
 * @author 
 */
@Table(name = "sys_group_app_role")
public class GroupAppRole implements Serializable {
    /**
     * 编号
     */
    @AssignID()
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 角色类型 字典值:1-PLATFORM_ADMIN-平台管理员,2-PLATFORM_OPERATOR-平台操作员,3-TENANT_ADMIN-租户管理员,4-COMPANY_ADMIN-企业管理员,5-TENANT_OPERATOR-租户操作员,0-NORMAL-普通角色
     */
    private Integer roleType;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 所属应用
     */
    @JSONField(serializeUsing= ToStringSerializer.class)
    private Long appId;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 是否启用 字典值：1-启用，0-未启用
     */
    private Boolean enableFlag;

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
    @UpdateIgnore
    private List<SensitiveDataVO> dataPermissionList;
    @UpdateIgnore
    private List<FunctionPermissionPointVO> pointPermissionList;
   
    @UpdateIgnore
    private List<RelRoleDataPermission> relDataPermission;
    @UpdateIgnore
    private List<RelRolePointPermission> relPointPermission;
    
    public List<RelRoleDataPermission> getRelDataPermission() {
		return relDataPermission;
	}

	public void setRelDataPermission(List<RelRoleDataPermission> relDataPermission) {
		this.relDataPermission = relDataPermission;
	}

	public List<RelRolePointPermission> getRelPointPermission() {
		return relPointPermission;
	}

	public void setRelPointPermission(List<RelRolePointPermission> relPointPermission) {
		this.relPointPermission = relPointPermission;
	}

	public List<SensitiveDataVO> getDataPermissionList() {
        return dataPermissionList;
    }

    public void setDataPermissionList(List<SensitiveDataVO> dataPermissionList) {
        this.dataPermissionList = dataPermissionList;
    }

    public List<FunctionPermissionPointVO> getPointPermissionList() {
        return pointPermissionList;
    }

    public void setPointPermissionList(List<FunctionPermissionPointVO> pointPermissionList) {
        this.pointPermissionList = pointPermissionList;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Boolean getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Boolean enableFlag) {
        this.enableFlag = enableFlag;
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
        GroupAppRole other = (GroupAppRole) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getRoleType() == null ? other.getRoleType() == null : this.getRoleType().equals(other.getRoleType()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getSeq() == null ? other.getSeq() == null : this.getSeq().equals(other.getSeq()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getEnableFlag() == null ? other.getEnableFlag() == null : this.getEnableFlag().equals(other.getEnableFlag()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoleType() == null) ? 0 : getRoleType().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getSeq() == null) ? 0 : getSeq().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getEnableFlag() == null) ? 0 : getEnableFlag().hashCode());
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
        sb.append(", roleType=").append(roleType);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", appId=").append(appId);
        sb.append(", seq=").append(seq);
        sb.append(", companyId=").append(companyId);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", enableFlag=").append(enableFlag);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}