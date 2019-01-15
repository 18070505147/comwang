package com.chejet.cloud.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/13
 */
public class DepartmentVO extends Page {
	/**
	 * 编号
	 */
	private Long id;

	/**
	 * 部门名称
	 */
	private String name;

	/**
	 * 部门描述
	 */
	private String description;

	/**
	 * 左值
	 */
	private Integer leftValue;

	/**
	 * 右值
	 */
	private Integer rightValue;

	/**
	 * 上级部门id
	 */
	private Long parentId;

	/**
	 * 所属企业id
	 */
	private Long companyId;

	/**
	 * 租户id
	 */
	private Long tenantId;

	/**
	 * 部门状态 true-启用，false-禁用
	 */
	private Boolean status;

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

	public Integer getLeftValue() {
		return leftValue;
	}

	public void setLeftValue(Integer leftValue) {
		this.leftValue = leftValue;
	}

	public Integer getRightValue() {
		return rightValue;
	}

	public void setRightValue(Integer rightValue) {
		this.rightValue = rightValue;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	private List<DepartmentVO> childrens = new ArrayList<DepartmentVO>();// 子级菜单

	public void addChildren(DepartmentVO departmentVO) {
		this.childrens.add(departmentVO);
	}

	public List<DepartmentVO> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<DepartmentVO> childrens) {
		this.childrens = childrens;
	}

	public void setEntity(DepartmentVO module) {

		setId(module.getId());

		setName(module.getName());

		setDescription(module.getDescription());

		setLeftValue(module.getLeftValue());

		setRightValue(module.getRightValue());

		setParentId(module.getParentId());

		setCompanyId(module.getCompanyId());
		setTenantId(module.getTenantId());
		setStatus(module.getStatus());
	}
}
