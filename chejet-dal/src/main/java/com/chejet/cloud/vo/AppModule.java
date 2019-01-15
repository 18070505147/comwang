package com.chejet.cloud.vo;

public class AppModule {
	Long appId;
	String appName;
	Long deploymoduleId;
	String deploymoduleName;
	String managerId;

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

	public Long getDeploymoduleId() {
		return deploymoduleId;
	}

	public void setDeploymoduleId(Long deploymoduleId) {
		this.deploymoduleId = deploymoduleId;
	}

	public String getDeploymoduleName() {
		return deploymoduleName;
	}

	public void setDeploymoduleName(String deploymoduleName) {
		this.deploymoduleName = deploymoduleName;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}
