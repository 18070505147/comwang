package com.chejet.cloud.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * sys_app_deploymodule
 * 
 * @author
 */
@Table(name = "sys_app_deploymodule")
public class AppDeploymodule implements Serializable {
	/**
	 * 编号
	 */
	@AssignID()
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	/**
	 * 配置包名称
	 */
	private String name;

	private String remark;

	/**
	 * app编码 关联平台应用表
	 */
	private Long appId;

	/**
	 * 创建时间
	 */
	private Date ctime;

	/**
	 * 更新时间
	 */
	private Date mtime;
	private List<AppFunction> appFunctionList;

	public List<AppFunction> getAppFunctionList() {
		return appFunctionList;
	}

	public void setAppFunctionList(List<AppFunction> appFunctionList) {
		this.appFunctionList = appFunctionList;
	}

	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		AppDeploymodule other = (AppDeploymodule) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
				&& (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
				&& (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
				&& (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
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
		sb.append(", name=").append(name);
		sb.append(", appId=").append(appId);
		sb.append(", ctime=").append(ctime);
		sb.append(", mtime=").append(mtime);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}