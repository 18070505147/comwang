package com.chejet.cloud.po;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/14 10:32
 */

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.Table;

@Table(name = "sys_app_version")
public class SysAppVersion {

    /**
     *销售版本id
     */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;
    /**
    应用id
    */
    private Long appId;
    /**
    描述
    */
    private String description;
    /**
    销售版本名称
    */
    private String name;
    private Date ctime;
    private Date mtime;

    public SysAppVersion() {
    }

    /**
     * 销售版本id
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * 销售版本id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 应用id
     *
     * @return
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * 应用id
     *
     * @param appId
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * 描述
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 销售版本名称
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 销售版本名称
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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


}
