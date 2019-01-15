
package com.chejet.cloud.vo;


public class AppRole {

	private Long id;

	/**
	 * 应用名称
	 */
	private String name;

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

	private Long roleId;
	
	private String roleName;
	
	private Long companyId;
	
	private Long tenantId;

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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
