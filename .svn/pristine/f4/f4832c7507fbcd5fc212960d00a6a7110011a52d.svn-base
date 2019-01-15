package com.chejet.cloud.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.TailBean;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

/**
 * sys_user_login_log
 * @author 
 */
@Table(name = "sys_user_login_log")
public class UserLoginLog extends TailBean implements Serializable {
    /**
     * 编号
     */
	@AssignID("simple")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 企业id
     */
    private Long companyId;

    /**
     * 租户id
     */
    private Long tenantId;
    /**
     * 应用id
     */
    private Long appId;

    /**
     * 登录状态 字典值：0-登录成功，1-密码、验证码匹配错误， 2-其他错误
     */
    private Integer loginStatus;

    /**
     * 系统类型 0-web,2-android,3-ios,4-wechat
     */
    private Integer loginDevice;

    /**
     * 设备信息
     */
    private String deviceInformation;

    /**
     * 用户token
     */
    private String userToken;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 备注
     */
    private String remark;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登出时间
     */
    private Date logoutTime;

    /**
     * 登录方式 字典值： 1-密码登录， 2-验证码登录
     */
    private Integer loginMode;


    private static final long serialVersionUID = 1L;

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

	public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Integer getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus = loginStatus;
    }

    public Integer getLoginDevice() {
        return loginDevice;
    }

    public void setLoginDevice(Integer loginDevice) {
        this.loginDevice = loginDevice;
    }

    public String getDeviceInformation() {
        return deviceInformation;
    }

    public void setDeviceInformation(String deviceInformation) {
        this.deviceInformation = deviceInformation;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Integer getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(Integer loginMode) {
        this.loginMode = loginMode;
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
        UserLoginLog other = (UserLoginLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getLoginStatus() == null ? other.getLoginStatus() == null : this.getLoginStatus().equals(other.getLoginStatus()))
            && (this.getLoginDevice() == null ? other.getLoginDevice() == null : this.getLoginDevice().equals(other.getLoginDevice()))
            && (this.getDeviceInformation() == null ? other.getDeviceInformation() == null : this.getDeviceInformation().equals(other.getDeviceInformation()))
            && (this.getUserToken() == null ? other.getUserToken() == null : this.getUserToken().equals(other.getUserToken()))
            && (this.getIp() == null ? other.getIp() == null : this.getIp().equals(other.getIp()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getLoginTime() == null ? other.getLoginTime() == null : this.getLoginTime().equals(other.getLoginTime()))
            && (this.getLogoutTime() == null ? other.getLogoutTime() == null : this.getLogoutTime().equals(other.getLogoutTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getLoginStatus() == null) ? 0 : getLoginStatus().hashCode());
        result = prime * result + ((getLoginDevice() == null) ? 0 : getLoginDevice().hashCode());
        result = prime * result + ((getDeviceInformation() == null) ? 0 : getDeviceInformation().hashCode());
        result = prime * result + ((getUserToken() == null) ? 0 : getUserToken().hashCode());
        result = prime * result + ((getIp() == null) ? 0 : getIp().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getLoginTime() == null) ? 0 : getLoginTime().hashCode());
        result = prime * result + ((getLogoutTime() == null) ? 0 : getLogoutTime().hashCode());
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
        sb.append(", companyId=").append(companyId);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", appId=").append(appId);
        sb.append(", loginStatus=").append(loginStatus);
        sb.append(", loginDevice=").append(loginDevice);
        sb.append(", deviceInformation=").append(deviceInformation);
        sb.append(", userToken=").append(userToken);
        sb.append(", ip=").append(ip);
        sb.append(", remark=").append(remark);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", logoutTime=").append(logoutTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}