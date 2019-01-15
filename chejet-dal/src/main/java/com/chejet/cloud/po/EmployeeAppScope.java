package com.chejet.cloud.po;

import java.io.Serializable;
import java.util.Date;

import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

/**
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/19
 */
@Table(name="sys_employee_app_scope")
public class EmployeeAppScope implements Serializable {

	/**
	 * 编号
	 */
	@AssignID()
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	/**
	 * 应用id
	 */
	private Long appId;

	private Long employeeId;

	private String scopeCode;
	private String scopeValue;

	/**
	 * 企业id
	 */
	private Long companyId;

	/**
	 * 租户id
	 */
	private Long tenantId;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 创建时间
	 */
	private Date ctime;

	/**
	 * 更新时间
	 */
	private Date mtime;

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getScopeCode() {
		return scopeCode;
	}

	public void setScopeCode(String scopeCode) {
		this.scopeCode = scopeCode;
	}

	public String getScopeValue() {
		return scopeValue;
	}

	public void setScopeValue(String scopeValue) {
		this.scopeValue = scopeValue;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
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
		EmployeeAppScope other = (EmployeeAppScope) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
				&& (this.getCompanyId() == null ? other.getCompanyId() == null
						: this.getCompanyId().equals(other.getCompanyId()))
				&& (this.getTenantId() == null ? other.getTenantId() == null
						: this.getTenantId().equals(other.getTenantId()))
				&& (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
				&& (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
				&& (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
		result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
		result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
		result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
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
		sb.append(", appId=").append(appId);
		sb.append(", companyId=").append(companyId);
		sb.append(", tenantId=").append(tenantId);
		sb.append(", userId=").append(userId);
		sb.append(", ctime=").append(ctime);
		sb.append(", mtime=").append(mtime);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}
