package com.chejet.cloud.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.chejet.cloud.po.AppFunction;
import com.chejet.cloud.po.FunctionPermissionPoint;
import com.chejet.cloud.po.SensitiveData;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/14 8:35
 */
public class AppFunctionVO implements Serializable {
    /**
     * 编号
     */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 功能名称
     */
    private String name;

    /**
     * 配置包模型id 平台sys_deploymodule->mid
     */
    private Long deploymoduleId;

    /**
     * 功能代码
     */
    private String code;

    /**
     * 图标url地址
     */
    private String iconUrl;

    /**
     * 功能url地址
     */
    private String functionUrl;

    /**
     * 功能分类 权限对象类型,字典值：0-FUN-功能；1-REPORT-报表；2-INVOICE-单据；3-SPECIAL-特殊
     */
    private Integer fucntionType;

    /**
     * 次序
     */
    private Integer seq;

    /**
     * 功能简介
     */
    private String description;

    /**
     * 功能的平台code 同步索引平台功能
     */
    private Integer functionPlatformCode;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 更新时间
     */
    private Date mtime;

    private List<FunctionPermissionPoint> functionPermissionPointsList;

    private List<SensitiveData> sensitiveDataList;

    public List<FunctionPermissionPoint> getFunctionPermissionPointsList() {
        return functionPermissionPointsList;
    }

    public void setFunctionPermissionPointsList(List<FunctionPermissionPoint> functionPermissionPointsList) {
        this.functionPermissionPointsList = functionPermissionPointsList;
    }

    public List<SensitiveData> getSensitiveDataList() {
        return sensitiveDataList;
    }

    public void setSensitiveDataList(List<SensitiveData> sensitiveDataList) {
        this.sensitiveDataList = sensitiveDataList;
    }

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

    public Long getDeploymoduleId() {
        return deploymoduleId;
    }

    public void setDeploymoduleId(Long deploymoduleId) {
        this.deploymoduleId = deploymoduleId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    public Integer getFucntionType() {
        return fucntionType;
    }

    public void setFucntionType(Integer fucntionType) {
        this.fucntionType = fucntionType;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFunctionPlatformCode() {
        return functionPlatformCode;
    }

    public void setFunctionPlatformCode(Integer functionPlatformCode) {
        this.functionPlatformCode = functionPlatformCode;
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
        AppFunction other = (AppFunction) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getDeploymoduleId() == null ? other.getDeploymoduleId() == null : this.getDeploymoduleId().equals(other.getDeploymoduleId()))
                && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
                && (this.getIconUrl() == null ? other.getIconUrl() == null : this.getIconUrl().equals(other.getIconUrl()))
                && (this.getFunctionUrl() == null ? other.getFunctionUrl() == null : this.getFunctionUrl().equals(other.getFunctionUrl()))
                && (this.getFucntionType() == null ? other.getFucntionType() == null : this.getFucntionType().equals(other.getFucntionType()))
                && (this.getSeq() == null ? other.getSeq() == null : this.getSeq().equals(other.getSeq()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
                && (this.getFunctionPlatformCode() == null ? other.getFunctionPlatformCode() == null : this.getFunctionPlatformCode().equals(other.getFunctionPlatformCode()))
                && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
                && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDeploymoduleId() == null) ? 0 : getDeploymoduleId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getIconUrl() == null) ? 0 : getIconUrl().hashCode());
        result = prime * result + ((getFunctionUrl() == null) ? 0 : getFunctionUrl().hashCode());
        result = prime * result + ((getFucntionType() == null) ? 0 : getFucntionType().hashCode());
        result = prime * result + ((getSeq() == null) ? 0 : getSeq().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getFunctionPlatformCode() == null) ? 0 : getFunctionPlatformCode().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", deploymoduleId=").append(deploymoduleId);
        sb.append(", code=").append(code);
        sb.append(", iconUrl=").append(iconUrl);
        sb.append(", functionUrl=").append(functionUrl);
        sb.append(", fucntionType=").append(fucntionType);
        sb.append(", seq=").append(seq);
        sb.append(", description=").append(description);
        sb.append(", functionPlatformCode=").append(functionPlatformCode);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
