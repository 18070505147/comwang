package com.chejet.cloud.po;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

/**
 * sys_user_operation_log
 * @author 
 */
@Table(name = "sys_user_operation_log")
public class UserOperationLog implements Serializable {
    /**
     * 编号
     */
	@AssignID("simple")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 操作类型 字典值：1-增加，2-删除，3-修改，4-查看，5-查询
     */
    private Integer operationType;

    /**
     * http类型
     */
    private String requestMethod;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 请求url api,params
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String queryParams;

    /**
     * 返回字段
     */
    private String returnFields;

    /**
     * 远程ip
     */
    private String remoteIpAddress;

    /**
     * 本地ip
     */
    private String localIpAddress;

    /**
     * 注释
     */
    private Long appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 类名
     */
    private Long tenantId;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 操作时间
     */
    private Date operationTime;
    
    /**
     * 操作描述
     */
    private String description;
    
    private String type;
    
    private String message;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams;
    }

    public String getReturnFields() {
        return returnFields;
    }

    public void setReturnFields(String returnFields) {
        this.returnFields = returnFields;
    }

    public String getRemoteIpAddress() {
        return remoteIpAddress;
    }

    public void setRemoteIpAddress(String remoteIpAddress) {
        this.remoteIpAddress = remoteIpAddress;
    }

    public String getLocalIpAddress() {
        return localIpAddress;
    }

    public void setLocalIpAddress(String localIpAddress) {
        this.localIpAddress = localIpAddress;
    }

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

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
        UserOperationLog other = (UserOperationLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOperationType() == null ? other.getOperationType() == null : this.getOperationType().equals(other.getOperationType()))
            && (this.getRequestMethod() == null ? other.getRequestMethod() == null : this.getRequestMethod().equals(other.getRequestMethod()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getRequestUrl() == null ? other.getRequestUrl() == null : this.getRequestUrl().equals(other.getRequestUrl()))
            && (this.getQueryParams() == null ? other.getQueryParams() == null : this.getQueryParams().equals(other.getQueryParams()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getReturnFields() == null ? other.getReturnFields() == null : this.getReturnFields().equals(other.getReturnFields()))
            && (this.getRemoteIpAddress() == null ? other.getRemoteIpAddress() == null : this.getRemoteIpAddress().equals(other.getRemoteIpAddress()))
            && (this.getLocalIpAddress() == null ? other.getLocalIpAddress() == null : this.getLocalIpAddress().equals(other.getLocalIpAddress()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getAppName() == null ? other.getAppName() == null : this.getAppName().equals(other.getAppName()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getTenantName() == null ? other.getTenantName() == null : this.getTenantName().equals(other.getTenantName()))
            && (this.getOperationTime() == null ? other.getOperationTime() == null : this.getOperationTime().equals(other.getOperationTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOperationType() == null) ? 0 : getOperationType().hashCode());
        result = prime * result + ((getRequestMethod() == null) ? 0 : getRequestMethod().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRequestUrl() == null) ? 0 : getRequestUrl().hashCode());
        result = prime * result + ((getQueryParams() == null) ? 0 : getQueryParams().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getReturnFields() == null) ? 0 : getReturnFields().hashCode());
        result = prime * result + ((getRemoteIpAddress() == null) ? 0 : getRemoteIpAddress().hashCode());
        result = prime * result + ((getLocalIpAddress() == null) ? 0 : getLocalIpAddress().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getAppName() == null) ? 0 : getAppName().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getTenantName() == null) ? 0 : getTenantName().hashCode());
        result = prime * result + ((getOperationTime() == null) ? 0 : getOperationTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", operationType=").append(operationType);
        sb.append(", requestMethod=").append(requestMethod);
        sb.append(", userId=").append(userId);
        sb.append(", requestUrl=").append(requestUrl);
        sb.append(", queryParams=").append(queryParams);
        sb.append(", type=").append(type);
        sb.append(", message=").append(message);
        sb.append(", returnFields=").append(returnFields);
        sb.append(", remoteIpAddress=").append(remoteIpAddress);
        sb.append(", localIpAddress=").append(localIpAddress);
        sb.append(", appId=").append(appId);
        sb.append(", appName=").append(appName);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", tenantName=").append(tenantName);
        sb.append(", operationTime=").append(operationTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}