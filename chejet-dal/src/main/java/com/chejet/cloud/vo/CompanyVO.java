package com.chejet.cloud.vo;

import org.beetl.sql.core.annotatoin.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/13
 */
@Table(name = "sys_company")
public class CompanyVO extends Page {
	/**
	 * 编号
	 */
	private Long id;

	/**
	 * 企业名称
	 */
	private String name;

	/**
	 * 左值
	 */
	private Integer leftValue;

	/**
	 * 右值
	 */
	private Integer rightValue;

	/**
	 * 上级企业id
	 */
	private Long parentId;

	/**
	 * 租户id
	 */
	private Long tenantId;

	/**
	 * 企业类型 关联字典表,字典值:1-法人企业,2-个人工商,3-其他
	 */
	private Integer enterpriseType;

	/**
	 * 企业属性 关联字典表,字典值:0-GROUP-集团,1-CHAIN-连锁,2-SINGLE-单店
	 */
	private Integer enterpriseAttribute;

	/**
	 * 企业法人
	 */
	private String legalPerson;

	/**
	 * 企业法人联系手机
	 */
	private String legalPersonPhone;

	/**
	 * 联系人
	 */
	private String contactPerson;

	/**
	 * 联系人手机
	 */
	private String contactPersonPhone;

	/**
	 * 企业地址
	 */
	private String address;
	
	private Integer counts;

	/**
	 * 详细地址
	 */
	private String fullAddress;

	/**
	 * 企业规模
	 * 关联字典表,字典值：0表示0-20,1表示21-99,2表示100-199,3表示200-500,4表示501-1000,5表示1000以上
	 */
	private Integer staffScale;

	/**
	 * 企业logo附件id
	 */
	private Long logoUrlId;

	/**
	 * 企业证书附件id
	 */
	private Long certificateUrlId;

	private String logoUrl;

	private String certificateUrl;

	/**
	 * 公司状态 false-禁用，true-启用
	 */
	private Integer companyStatus;

	/**
	 * 注册时间
	 */
	private Date registerTime;

	/**
	 * 注册地
	 */
	private String registerAddress;

	/**
	 * 注册号
	 */
	private String registerNo;

	/**
	 * 组织机构代码
	 */
	private String organizationNo;

	/**
	 * 纳税人识别号
	 */
	private String taxpayerIdNo;

	/**
	 * 统一信用代码
	 */
	private String uscCode;

	/**
	 * 公司所属行业
	 */
	private String companyIndustry;

	/**
	 * 公司所属地区
	 */
	private String companyRegin;

	/**
	 * 英文名称
	 */
	private String englishName;

	/**
	 * 企业邮箱
	 */
	private String enterpriseEmail;


	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getCertificateUrl() {
		return certificateUrl;
	}

	public void setCertificateUrl(String certificateUrl) {
		this.certificateUrl = certificateUrl;
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

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Integer getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(Integer enterpriseType) {
		this.enterpriseType = enterpriseType;
	}

	public Integer getEnterpriseAttribute() {
		return enterpriseAttribute;
	}

	public void setEnterpriseAttribute(Integer enterpriseAttribute) {
		this.enterpriseAttribute = enterpriseAttribute;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalPersonPhone() {
		return legalPersonPhone;
	}

	public void setLegalPersonPhone(String legalPersonPhone) {
		this.legalPersonPhone = legalPersonPhone;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactPersonPhone() {
		return contactPersonPhone;
	}

	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public Integer getStaffScale() {
		return staffScale;
	}

	public void setStaffScale(Integer staffScale) {
		this.staffScale = staffScale;
	}

	public Long getLogoUrlId() {
		return logoUrlId;
	}

	public void setLogoUrlId(Long logoUrlId) {
		this.logoUrlId = logoUrlId;
	}

	public Long getCertificateUrlId() {
		return certificateUrlId;
	}

	public void setCertificateUrlId(Long certificateUrlId) {
		this.certificateUrlId = certificateUrlId;
	}

	public Integer getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(Integer companyStatus) {
		this.companyStatus = companyStatus;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterAddress() {
		return registerAddress;
	}

	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}

	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	public String getOrganizationNo() {
		return organizationNo;
	}

	public void setOrganizationNo(String organizationNo) {
		this.organizationNo = organizationNo;
	}

	public String getTaxpayerIdNo() {
		return taxpayerIdNo;
	}

	public void setTaxpayerIdNo(String taxpayerIdNo) {
		this.taxpayerIdNo = taxpayerIdNo;
	}

	public String getUscCode() {
		return uscCode;
	}

	public void setUscCode(String uscCode) {
		this.uscCode = uscCode;
	}

	public String getCompanyIndustry() {
		return companyIndustry;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}

	public String getCompanyRegin() {
		return companyRegin;
	}

	public void setCompanyRegin(String companyRegin) {
		this.companyRegin = companyRegin;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getEnterpriseEmail() {
		return enterpriseEmail;
	}

	public void setEnterpriseEmail(String enterpriseEmail) {
		this.enterpriseEmail = enterpriseEmail;
	}

	private List<CompanyVO> childrens = new ArrayList<CompanyVO>();// 子级菜单

	public void addChildren(CompanyVO companyVO) {
		this.childrens.add(companyVO);
	}

	public List<CompanyVO> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<CompanyVO> childrens) {
		this.childrens = childrens;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public void setEntity(CompanyVO module) {

		setId(module.getId());

		setName(module.getName());

		setLeftValue(module.getLeftValue());

		setRightValue(module.getRightValue());

		setParentId(module.getParentId());

		setTenantId(module.getTenantId());

		setEnterpriseType(module.getEnterpriseType());

		setEnterpriseAttribute(module.getEnterpriseAttribute());

		setLegalPerson(module.getLegalPerson());

		setLegalPersonPhone(module.getLegalPersonPhone());

		setContactPerson(module.getContactPerson());

		setContactPersonPhone(module.getContactPersonPhone());

		setAddress(module.getAddress());

		setFullAddress(module.getFullAddress());

		setStaffScale(module.getStaffScale());

		setLogoUrlId(module.getLogoUrlId());

		setCertificateUrlId(module.getCertificateUrlId());

		setCompanyStatus(module.getCompanyStatus());

		setRegisterTime(module.getRegisterTime());

		setRegisterAddress(module.getRegisterAddress());

		setRegisterNo(module.getRegisterNo());

		setOrganizationNo(module.getOrganizationNo());

		setTaxpayerIdNo(module.getTaxpayerIdNo());

		setUscCode(module.getUscCode());

		setCompanyIndustry(module.getCompanyIndustry());

		setCompanyRegin(module.getCompanyRegin());

		setEnglishName(module.getEnglishName());

		setEnterpriseEmail(module.getEnterpriseEmail());
		
		setCounts(module.getCounts());
	}
}
