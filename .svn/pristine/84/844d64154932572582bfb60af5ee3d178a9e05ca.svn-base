package com.chejet.cloud.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.annotatoin.UpdateIgnore;

import com.chejet.cloud.excel.annotation.ExcelField;
import com.chejet.cloud.vo.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * sys_employee
 * @author 
 */
@Table(name="sys_employee")
public class Employee extends Page implements Serializable {
    /**
     * 编号
     */
    @AssignID("simple")
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 员工名称 关联用户表
     */
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
     *部门名称
     */
    private String departmentName;

    private Long tenantId;
    
    private Long companyId;

    /**
     * 岗位
     */
    private String job;

    /**
     * 岗位编号
     */
    private String jobNumber;

    /**
     * 员工状态 关联字典表，字典值：true在职，false离职
     */
    private Integer status;

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
     * 创建时间
     */
    @JsonIgnore
    private Date ctime;

    /**
     * 更新时间
     */
    @JsonIgnore
    private Date mtime;

    private String name;

	private String telephone;

	@UpdateIgnore
	private List<RelUserGroupRole> relRoleList;
	
	@UpdateIgnore
	private EmployeeAppScope scopePermisson;

	@UpdateIgnore
	private List<GroupAppRole> groupAppRole;
	
	private Long appId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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

	public List<RelUserGroupRole> getRelRoleList() {
		return relRoleList;
	}

	public void setRelRoleList(List<RelUserGroupRole> relRoleList) {
		this.relRoleList = relRoleList;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}
	

	public EmployeeAppScope getScopePermisson() {
		return scopePermisson;
	}

	public void setScopePermisson(EmployeeAppScope scopePermisson) {
		this.scopePermisson = scopePermisson;
	}

	public List<GroupAppRole> getGroupAppRole() {
		return groupAppRole;
	}

	public void setGroupAppRole(List<GroupAppRole> groupAppRole) {
		this.groupAppRole = groupAppRole;
	}

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
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
        Employee other = (Employee) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getJob() == null ? other.getJob() == null : this.getJob().equals(other.getJob()))
            && (this.getJobNumber() == null ? other.getJobNumber() == null : this.getJobNumber().equals(other.getJobNumber()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getManageScopeType() == null ? other.getManageScopeType() == null : this.getManageScopeType().equals(other.getManageScopeType()))
            && (this.getManageDepartmentId() == null ? other.getManageDepartmentId() == null : this.getManageDepartmentId().equals(other.getManageDepartmentId()))
            && (this.getEntryDate() == null ? other.getEntryDate() == null : this.getEntryDate().equals(other.getEntryDate()))
            && (this.getLeaveDate() == null ? other.getLeaveDate() == null : this.getLeaveDate().equals(other.getLeaveDate()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getJob() == null) ? 0 : getJob().hashCode());
        result = prime * result + ((getJobNumber() == null) ? 0 : getJobNumber().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getManageScopeType() == null) ? 0 : getManageScopeType().hashCode());
        result = prime * result + ((getManageDepartmentId() == null) ? 0 : getManageDepartmentId().hashCode());
        result = prime * result + ((getEntryDate() == null) ? 0 : getEntryDate().hashCode());
        result = prime * result + ((getLeaveDate() == null) ? 0 : getLeaveDate().hashCode());
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
        sb.append(", userName=").append(userName);
        sb.append(", userId=").append(userId);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", companyId=").append(companyId);
        sb.append(", tenantId=").append(tenantId);
        sb.append(", job=").append(job);
        sb.append(", jobNumber=").append(jobNumber);
        sb.append(", status=").append(status);
        sb.append(", email=").append(email);
        sb.append(", manageScopeType=").append(manageScopeType);
        sb.append(", manageDepartmentId=").append(manageDepartmentId);
        sb.append(", entryDate=").append(entryDate);
        sb.append(", leaveDate=").append(leaveDate);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}