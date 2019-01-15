package com.chejet.cloud.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;

/**
 * sys_sensitive_data
 *
 * @author
 */
@Table(name = "sys_sensitive_data")
public class SensitiveData implements Serializable {
    /**
     * 编号
     */
    @AssignID()
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 字段代码
     */
    private String dataCode;

    /**
     * 表字段名称
     */
    private String dataName;

    /**
     * 数据表字段
     */
    private String dataColumn;

    /**
     * 数据库表
     */
    private String dataTable;

    /**
     * 数据库实例
     */
    private String dataSchema;

    /**
     * 数据源
     */
    private String dataSource;

    /**
     * 功能id
     */
    private String functionId;

    /**
     * 应用id
     */
    private Long appId;

    /**
     * 注释
     */
    private String remark;

    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 创建日期
     */
    private Date ctime;

    /**
     * 修改日期
     */
    private Date mtime;

    private List<SensitiveData> child;

    public List<SensitiveData> getChild() {
        return child;
    }

    public void setChild(List<SensitiveData> child) {
        this.child = child;
    }

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataColumn() {
        return dataColumn;
    }

    public void setDataColumn(String dataColumn) {
        this.dataColumn = dataColumn;
    }

    public String getDataTable() {
        return dataTable;
    }

    public void setDataTable(String dataTable) {
        this.dataTable = dataTable;
    }

    public String getDataSchema() {
        return dataSchema;
    }

    public void setDataSchema(String dataSchema) {
        this.dataSchema = dataSchema;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getFunctionId() {
        return functionId;
    }

    public void setFunctionId(String functionId) {
        this.functionId = functionId;
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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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
        SensitiveData other = (SensitiveData) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getDataCode() == null ? other.getDataCode() == null : this.getDataCode().equals(other.getDataCode()))
                && (this.getDataName() == null ? other.getDataName() == null : this.getDataName().equals(other.getDataName()))
                && (this.getDataColumn() == null ? other.getDataColumn() == null : this.getDataColumn().equals(other.getDataColumn()))
                && (this.getDataTable() == null ? other.getDataTable() == null : this.getDataTable().equals(other.getDataTable()))
                && (this.getDataSchema() == null ? other.getDataSchema() == null : this.getDataSchema().equals(other.getDataSchema()))
                && (this.getDataSource() == null ? other.getDataSource() == null : this.getDataSource().equals(other.getDataSource()))
                && (this.getFunctionId() == null ? other.getFunctionId() == null : this.getFunctionId().equals(other.getFunctionId()))
                && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
                && (this.getCreatorName() == null ? other.getCreatorName() == null : this.getCreatorName().equals(other.getCreatorName()))
                && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
                && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getDataCode() == null) ? 0 : getDataCode().hashCode());
        result = prime * result + ((getDataName() == null) ? 0 : getDataName().hashCode());
        result = prime * result + ((getDataColumn() == null) ? 0 : getDataColumn().hashCode());
        result = prime * result + ((getDataTable() == null) ? 0 : getDataTable().hashCode());
        result = prime * result + ((getDataSchema() == null) ? 0 : getDataSchema().hashCode());
        result = prime * result + ((getDataSource() == null) ? 0 : getDataSource().hashCode());
        result = prime * result + ((getFunctionId() == null) ? 0 : getFunctionId().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreatorName() == null) ? 0 : getCreatorName().hashCode());
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
        sb.append(", dataCode=").append(dataCode);
        sb.append(", dataName=").append(dataName);
        sb.append(", dataColumn=").append(dataColumn);
        sb.append(", dataTable=").append(dataTable);
        sb.append(", dataSchema=").append(dataSchema);
        sb.append(", dataSource=").append(dataSource);
        sb.append(", functionId=").append(functionId);
        sb.append(", appId=").append(appId);
        sb.append(", remark=").append(remark);
        sb.append(", creatorName=").append(creatorName);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}