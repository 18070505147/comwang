package com.chejet.cloud.vo;

import com.chejet.cloud.excel.annotation.ExcelField;

import java.util.Date;

/**
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/13
 */
public class EmployeeVO extends Page {
	/**
	 * 编号
	 */
	private Long id;

	private String name;

	/**
	 * 顺序
	 */
	@ExcelField(title = "序号", sort = 1)
	private Integer sort;

	@ExcelField(title = "手机号", sort = 3)
	private String telephone;

	/**
	 * 员工名称 关联用户表
	 */
	@ExcelField(title = "姓名",sort = 2)
	private String userName;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 部门id
	 */
	private Long departmentId;

	/**
	 * 部门名称
	 */
	@ExcelField(title="所在部门", sort=4)
	private String departmentName;

	/**
	 * 公司id
	 */
	private Long companyId;

	/**
	 * 企业名称
	 */
	private String companyName;

	/**
	 * 租户id
	 */
	private Long tenantId;

	/**
	 * 岗位
	 */
	@ExcelField(title="岗位", sort=5)
	private String job;

	/**
	 * 岗位编号
	 */
	@ExcelField(title="员工工号", sort=6)
	private String jobNumber;

	/**
	 * 员工状态 关联字典表，字典值：true在职，false离职
	 */
	private Integer status;

	/**
	 * 状态中文
	 */
	@ExcelField(title="状态", sort=7)
	private String statusChs;

	/**
	 * 员工邮箱
	 */
	private String email;

	/**
	 * 管理范围类型 关联字典表，字典值：0-全公司，1-所在部门及下级部门，2-特定部门
	 */
	private Integer manageScopeType;

	/**
	 * 管理特定部门id
	 */
	private Long manageDepartmentId;

	/**
	 * 入职日期
	 */
	private Date entryDate;

	/**
	 * 离职日期
	 */
	private Date leaveDate;

	/**
	 * 用户头像附件id 用户头像
	 */
	private String portraitUrl;

	/**
	 * 用户头像资源ID
	 */
	private Long portraitUrlId;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getPortraitUrl() {
		return portraitUrl;
	}

	public void setPortraitUrl(String portraitUrl) {
		this.portraitUrl = portraitUrl;
	}

	public Long getPortraitUrlId() {
		return portraitUrlId;
	}

	public void setPortraitUrlId(Long portraitUrlId) {
		this.portraitUrlId = portraitUrlId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getManageScopeType() {
		return manageScopeType;
	}

	public void setManageScopeType(Integer manageScopeType) {
		this.manageScopeType = manageScopeType;
	}

	public Long getManageDepartmentId() {
		return manageDepartmentId;
	}

	public void setManageDepartmentId(Long manageDepartmentId) {
		this.manageDepartmentId = manageDepartmentId;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStatusChs() {
		return statusChs;
	}

	public void setStatusChs(String statusChs) {
		this.statusChs = statusChs;
	}
}
