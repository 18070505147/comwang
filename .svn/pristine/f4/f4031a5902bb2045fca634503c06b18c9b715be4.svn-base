package com.chejet.cloud.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.AssignID;

import java.io.Serializable;
import java.util.Date;

/**
 * sys_parameter
 * @author 
 */
public class Parameter implements Serializable {
    /**
     * 编号
     */
    @AssignID()
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 参数代码
     */
    private Integer code;

    /**
     * 参数描述
     */
    private String description;

    /**
     * 参数类型字典值 字段规格类型(type)
     */
    private Integer parameterType;

    /**
     * 参数值
     */
    private String value;

    /**
     * 适用范围字典值 字典值：0-平台内，1-租户内，2-企业内
     */
    private Integer suitedScope;

    /**
     * 适用对象字典值 字典值：0-租户，1-企业
     */
    private Integer suitedObject;

    /**
     * 备注说明
     */
    private String remark;

    /**
     * 创建人姓名
     */
    private String createName;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 更新时间
     */
    private Date mtime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParameterType() {
        return parameterType;
    }

    public void setParameterType(Integer parameterType) {
        this.parameterType = parameterType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSuitedScope() {
        return suitedScope;
    }

    public void setSuitedScope(Integer suitedScope) {
        this.suitedScope = suitedScope;
    }

    public Integer getSuitedObject() {
        return suitedObject;
    }

    public void setSuitedObject(Integer suitedObject) {
        this.suitedObject = suitedObject;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
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
        Parameter other = (Parameter) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getParameterType() == null ? other.getParameterType() == null : this.getParameterType().equals(other.getParameterType()))
            && (this.getValue() == null ? other.getValue() == null : this.getValue().equals(other.getValue()))
            && (this.getSuitedScope() == null ? other.getSuitedScope() == null : this.getSuitedScope().equals(other.getSuitedScope()))
            && (this.getSuitedObject() == null ? other.getSuitedObject() == null : this.getSuitedObject().equals(other.getSuitedObject()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCreateName() == null ? other.getCreateName() == null : this.getCreateName().equals(other.getCreateName()))
            && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
            && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getParameterType() == null) ? 0 : getParameterType().hashCode());
        result = prime * result + ((getValue() == null) ? 0 : getValue().hashCode());
        result = prime * result + ((getSuitedScope() == null) ? 0 : getSuitedScope().hashCode());
        result = prime * result + ((getSuitedObject() == null) ? 0 : getSuitedObject().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getCreateName() == null) ? 0 : getCreateName().hashCode());
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
        sb.append(", code=").append(code);
        sb.append(", description=").append(description);
        sb.append(", parameterType=").append(parameterType);
        sb.append(", value=").append(value);
        sb.append(", suitedScope=").append(suitedScope);
        sb.append(", suitedObject=").append(suitedObject);
        sb.append(", remark=").append(remark);
        sb.append(", createName=").append(createName);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}