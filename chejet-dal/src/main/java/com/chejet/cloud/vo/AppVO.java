package com.chejet.cloud.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.chejet.cloud.po.GroupAppRole;
import com.chejet.cloud.po.SysAppVersion;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import java.util.List;

import org.beetl.sql.core.SQLReady;

public class AppVO {
	/**
	 * 编号
	 */
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	/**
	 * 应用名称
	 */
	private String name;

	/**
	 * 应用描述
	 */
	private String description;

	/**
	 * 应用类别 字典值：1-平台内部应用，2-平台共享应用，3-平台特定应用，4-公司内部自建应用，5-公司共享自建应用，6-公司内部自有应用
	 */
	private Integer appType;

	/**
	 * 首页地址
	 */
	private String homeUrl;

	/**
	 * 应用编号 编码(app_token)
	 */
	private String appKey;

	/**
	 * 应用密钥 应用密钥(secret_id)
	 */
	private String appSecret;

	/**
	 * app版本
	 */
	private String appVersion;

	/**
	 * 显示名称
	 */
	private String displayName;

	/**
	 * 应用图标url 应用图标(logo)
	 */
	private String iconUrl;

	/**
	 * 陈列展示图片url
	 */
	private String exhibitImageUrl;

	/**
	 * 是否启用 字典值：true-启用，false-未启用
	 */
	private Boolean enableFlag;

	/**
	 * 排序
	 */
	private Integer seq;
	
	

    /**
     * 过期时间
     */
//    @JsonFormat(pattern = "YYYY-MM-dd")
    private String expireTime;

    private Integer appStatus;

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public Integer getAppStatus() {
		return appStatus;
	}

	public void setAppStatus(Integer appStatus) {
		this.appStatus = appStatus;
	}

	/**
	 * 应用平台code 同步索引平台应用
	 */
	private Integer platformAppCode;

	private List<SysAppVersion> versionList;

	public List<SysAppVersion> getVersionList() {
		return versionList;
	}

	public void setVersionList(List<SysAppVersion> versionList) {
		this.versionList = versionList;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getExhibitImageUrl() {
		return exhibitImageUrl;
	}

	public void setExhibitImageUrl(String exhibitImageUrl) {
		this.exhibitImageUrl = exhibitImageUrl;
	}

	public Boolean getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(Boolean enableFlag) {
		this.enableFlag = enableFlag;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getPlatformAppCode() {
		return platformAppCode;
	}

	public void setPlatformAppCode(Integer platformAppCode) {
		this.platformAppCode = platformAppCode;
	}
}
