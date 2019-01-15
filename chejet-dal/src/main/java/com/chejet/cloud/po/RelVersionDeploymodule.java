package com.chejet.cloud.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/14 21:04
 */
@Table(name = "rel_version_deploymodule")
public class RelVersionDeploymodule {

	/*
	 * 编号
	 */
	@AssignID()
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;
	/*
	 * app编码 关联平台应用表
	 */
	private Long appId;
	/*
	 * 配置包平台code 同步索引平台配置包
	 */
	private Long deploymoduleId;
	
	private Long versionId;
	/*
	 * 创建时间
	 */
	private Date ctime;
	/*
	 * 更新时间
	 */
	private Date mtime;

	public RelVersionDeploymodule() {
	}

	/**
	 * 编号
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 编号
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * app编码 关联平台应用表
	 * 
	 * @return
	 */
	public Long getAppId() {
		return appId;
	}

	/**
	 * app编码 关联平台应用表
	 * 
	 * @param appId
	 */
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	public Long getDeploymoduleId() {
		return deploymoduleId;
	}

	public void setDeploymoduleId(Long deploymoduleId) {
		this.deploymoduleId = deploymoduleId;
	}

	public Long getVersionId() {
		return versionId;
	}

	public void setVersionId(Long versionId) {
		this.versionId = versionId;
	}

	/**
	 * 创建时间
	 * 
	 * @return
	 */
	public Date getCtime() {
		return ctime;
	}

	/**
	 * 创建时间
	 * 
	 * @param ctime
	 */
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	/**
	 * 更新时间
	 * 
	 * @return
	 */
	public Date getMtime() {
		return mtime;
	}

	/**
	 * 更新时间
	 * 
	 * @param mtime
	 */
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

}
