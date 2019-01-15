package com.chejet.cloud.po;

import com.alibaba.fastjson.annotation.JSONField;

import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.annotatoin.UpdateIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * sys_app
 *
 * @author
 */
@Table(name = "sys_app")
public class App implements Serializable {
    /**
     * 编号
     */
    @AssignID()
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用描述
     */
    private String description;

    /**
     * 应用类别 字典值：1-平台内部应用，2-平台共享应用，3-平台特定应用，4-公司内部自建应用，5-公司共享自建应用，6-公司内部自有应用
     */
    private Integer appType;

    /**
     * 首页地址
     */
    private String homeUrl;

    /**
     * 应用编号 编码(app_token)
     */
    private String appKey;

    /**
     * 应用密钥 应用密钥(secret_id)
     */
    private String appSecret;

    /**
     * app版本
     */
    private String appVersion;

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

    /**
     * 是否启用 字典值：true-启用，false-未启用
     */
    private Boolean enableFlag;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 应用平台code 同步索引平台应用
     */
    private Integer platformAppCode;

    /**
     * 创建时间
     */
    private Date ctime;

    /**
     * 更新时间
     */
    private Date mtime;
    
    private Long CompanyId;
    private Long TenantId;
    
    List<GroupAppRole> listAppRole;

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

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
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

    public Boolean getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(Boolean enableFlag) {
        this.enableFlag = enableFlag;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getPlatformAppCode() {
        return platformAppCode;
    }

    public void setPlatformAppCode(Integer platformAppCode) {
        this.platformAppCode = platformAppCode;
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
    

    public List<GroupAppRole> getListAppRole() {
		return listAppRole;
	}

	public void setListAppRole(List<GroupAppRole> listAppRole) {
		this.listAppRole = listAppRole;
	}

	public Long getCompanyId() {
		return CompanyId;
	}

	public void setCompanyId(Long companyId) {
		CompanyId = companyId;
	}

	public Long getTenantId() {
		return TenantId;
	}

	public void setTenantId(Long tenantId) {
		TenantId = tenantId;
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
        App other = (App) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
                && (this.getAppType() == null ? other.getAppType() == null : this.getAppType().equals(other.getAppType()))
                && (this.getHomeUrl() == null ? other.getHomeUrl() == null : this.getHomeUrl().equals(other.getHomeUrl()))
                && (this.getAppKey() == null ? other.getAppKey() == null : this.getAppKey().equals(other.getAppKey()))
                && (this.getAppSecret() == null ? other.getAppSecret() == null : this.getAppSecret().equals(other.getAppSecret()))
                && (this.getAppVersion() == null ? other.getAppVersion() == null : this.getAppVersion().equals(other.getAppVersion()))
                && (this.getDisplayName() == null ? other.getDisplayName() == null : this.getDisplayName().equals(other.getDisplayName()))
                && (this.getIconUrl() == null ? other.getIconUrl() == null : this.getIconUrl().equals(other.getIconUrl()))
                && (this.getExhibitImageUrl() == null ? other.getExhibitImageUrl() == null : this.getExhibitImageUrl().equals(other.getExhibitImageUrl()))
                && (this.getEnableFlag() == null ? other.getEnableFlag() == null : this.getEnableFlag().equals(other.getEnableFlag()))
                && (this.getSeq() == null ? other.getSeq() == null : this.getSeq().equals(other.getSeq()))
                && (this.getPlatformAppCode() == null ? other.getPlatformAppCode() == null : this.getPlatformAppCode().equals(other.getPlatformAppCode()))
                && (this.getCtime() == null ? other.getCtime() == null : this.getCtime().equals(other.getCtime()))
                && (this.getMtime() == null ? other.getMtime() == null : this.getMtime().equals(other.getMtime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getAppType() == null) ? 0 : getAppType().hashCode());
        result = prime * result + ((getHomeUrl() == null) ? 0 : getHomeUrl().hashCode());
        result = prime * result + ((getAppKey() == null) ? 0 : getAppKey().hashCode());
        result = prime * result + ((getAppSecret() == null) ? 0 : getAppSecret().hashCode());
        result = prime * result + ((getAppVersion() == null) ? 0 : getAppVersion().hashCode());
        result = prime * result + ((getDisplayName() == null) ? 0 : getDisplayName().hashCode());
        result = prime * result + ((getIconUrl() == null) ? 0 : getIconUrl().hashCode());
        result = prime * result + ((getExhibitImageUrl() == null) ? 0 : getExhibitImageUrl().hashCode());
        result = prime * result + ((getEnableFlag() == null) ? 0 : getEnableFlag().hashCode());
        result = prime * result + ((getSeq() == null) ? 0 : getSeq().hashCode());
        result = prime * result + ((getPlatformAppCode() == null) ? 0 : getPlatformAppCode().hashCode());
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
        sb.append(", description=").append(description);
        sb.append(", appType=").append(appType);
        sb.append(", homeUrl=").append(homeUrl);
        sb.append(", appKey=").append(appKey);
        sb.append(", appSecret=").append(appSecret);
        sb.append(", appVersion=").append(appVersion);
        sb.append(", displayName=").append(displayName);
        sb.append(", iconUrl=").append(iconUrl);
        sb.append(", exhibitImageUrl=").append(exhibitImageUrl);
        sb.append(", enableFlag=").append(enableFlag);
        sb.append(", seq=").append(seq);
        sb.append(", platformAppCode=").append(platformAppCode);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}