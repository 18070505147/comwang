package com.chejet.cloud.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.annotatoin.UpdateIgnore;

import com.chejet.cloud.vo.DepartmentVO;
import com.chejet.cloud.vo.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * sys_department
 * @author 
 */
@Table(name = "sys_department")
public class Department extends Page implements Serializable {
    /**
     * 编号
     */
    @AssignID("simple")
    @JSONField(serializeUsing = ToStringSerializer.class)
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
    @JsonIgnore
    private Integer leftValue;

    /**
     * 右值
     */
    @JsonIgnore
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
     * 部门状态 true-启用，false-禁用
     */
    private Integer departmentStatus;

    /**
     * 录入人
     */
    @JsonIgnore
    private Long createBy;

    /**
     * 创建时间
     */
    @JsonIgnore
    private Date ctime;

    /**
     * 更新人
     */
    @JsonIgnore
    private Long modifiedBy;

    /**
     * 更新时间
     */
    @JsonIgnore
    private Date mtime;
    
    @JsonIgnore
    private Long tenantId;
    

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
    
    public Integer getDepartmentStatus() {
		return departmentStatus;
	}

	public void setDepartmentStatus(Integer departmentStatus) {
		this.departmentStatus = departmentStatus;
	}

	public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
    
    public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
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
        Department other = (Department) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTenantId() == null ? other.getTenantId() == null : this.getTenantId().equals(other.getTenantId()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getLeftValue() == null ? other.getLeftValue() == null : this.getLeftValue().equals(other.getLeftValue()))
            && (this.getRightValue() == null ? other.getRightValue() == null : this.getRightValue().equals(other.getRightValue()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getDepartmentStatus() == null ? other.getDepartmentStatus() == null : this.getDepartmentStatus().equals(other.getDepartmentStatus()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getModifiedBy() == null ? other.getModifiedBy() == null : this.getModifiedBy().equals(other.getModifiedBy()))
            && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTenantId() == null) ? 0 : getTenantId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getLeftValue() == null) ? 0 : getLeftValue().hashCode());
        result = prime * result + ((getRightValue() == null) ? 0 : getRightValue().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getDepartmentStatus() == null) ? 0 : getDepartmentStatus().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCtime() == null) ? 0 : getCtime().hashCode());
        result = prime * result + ((getModifiedBy() == null) ? 0 : getModifiedBy().hashCode());
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
        sb.append(", description=").append(description);
        sb.append(", leftValue=").append(leftValue);
        sb.append(", rightValue=").append(rightValue);
        sb.append(", parentId=").append(parentId);
        sb.append(", companyId=").append(companyId);
        sb.append(",tenantId=").append(tenantId);
        sb.append(", departmentStatus=").append(departmentStatus);
        sb.append(", createBy=").append(createBy);
        sb.append(", ctime=").append(ctime);
        sb.append(", modifiedBy=").append(modifiedBy);
        sb.append(", mtime=").append(mtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
    
    @UpdateIgnore
    private List<Department> childrens = new ArrayList<Department>();// 子级菜单

	public void addChildren(Department department) {
		this.childrens.add(department);
	}

	public List<Department> getChildrens() {
		return childrens;
	}

	public void setChildrens(List<Department> childrens) {
		this.childrens = childrens;
	}

	public void setEntity(Department module) {
		setId(module.getId());
		setName(module.getName());
		setDescription(module.getDescription());
		setLeftValue(module.getLeftValue());
		setRightValue(module.getRightValue());
		setParentId(module.getParentId());
		setCompanyId(module.getCompanyId());
		setTenantId(module.getTenantId());
		setDepartmentStatus(module.getDepartmentStatus());
	}
}