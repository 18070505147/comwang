package com.chejet.cloud.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

/**
 * sys_user_account_log
 * @author 
 */
@Table(name = "sys_user_account_log")
public class UserAccountLog implements Serializable {
    /**
     * 编号
     */
    @AssignID()
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 变更前账号
     */
    private String initalUsername;

    /**
     * 变更后账号
     */
    private String modifiedUsername;

    /**
     * 业务类型 字典值:0-注册,1-重置手机,2-添加员工
     */
    private Integer businessType;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 设备信息 浏览器或者手机的信息
     */
    private String deviceInformation;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 变更时间
     */
    private Date ctime;
    
    /**
     * 日志类型
     */
    private String type;

    /**
     * 操作人
     */
    private String operationUser;

    private static final long serialVersionUID = 1L;

    public String getOperationUser() {
        return operationUser;
    }

    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getInitalUsername() {
        return initalUsername;
    }

    public void setInitalUsername(String initalUsername) {
        this.initalUsername = initalUsername;
    }

    public String getModifiedUsername() {
        return modifiedUsername;
    }

    public void setModifiedUsername(String modifiedUsername) {
        this.modifiedUsername = modifiedUsername;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDeviceInformation() {
        return deviceInformation;
    }

    public void setDeviceInformation(String deviceInformation) {
        this.deviceInformation = deviceInformation;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
        UserAccountLog other = (UserAccountLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getInitalUsername() == null ? other.getInitalUsername() == null : this.getInitalUsername().equals(other.getInitalUsername()))
            && (this.getModifiedUsername() == null ? other.getModifiedUsername() == null : this.getModifiedUsername().equals(other.getModifiedUsername()))
            && (this.getBusinessType() == null ? other.getBusinessType() == null : this.getBusinessType().equals(other.getBusinessType()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getDeviceInformation() == null ? other.getDeviceInformation() == null : this.getDeviceInformation().equals(other.getDeviceInformation()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantName() == null ? other.getTenantName() == null : this.getTenantName().equals(other.getTenantName()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getInitalUsername() == null) ? 0 : getInitalUsername().hashCode());
        result = prime * result + ((getModifiedUsername() == null) ? 0 : getModifiedUsername().hashCode());
        result = prime * result + ((getBusinessType() == null) ? 0 : getBusinessType().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getDeviceInformation() == null) ? 0 : getDeviceInformation().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getTenantName() == null) ? 0 : getTenantName().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", initalUsername=").append(initalUsername);
        sb.append(", modifiedUsername=").append(modifiedUsername);
        sb.append(", businessType=").append(businessType);
        sb.append(", ip=").append(ip);
        sb.append(", deviceInformation=").append(deviceInformation);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", tenantName=").append(tenantName);
        sb.append(", companyId=").append(companyId);
        sb.append(", companyName=").append(companyName);
        sb.append(", ctime=").append(ctime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	
}