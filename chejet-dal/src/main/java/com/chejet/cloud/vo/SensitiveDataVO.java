package com.chejet.cloud.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import org.beetl.sql.core.annotatoin.Table;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/18 15:01
 */
@Table(name = "rel_role_data_permission")
public class SensitiveDataVO {
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long sentitiveId;
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long roleId;
    /**
     * 修改 false-不可修改，true-可修改
     */
    private Boolean modifiedFlag;

    /**
     * 查看 false-不可查看，true-可查看
     */
    private Boolean viewFlag;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getSentitiveId() {
        return sentitiveId;
    }

    public void setSentitiveId(Long sentitiveId) {
        this.sentitiveId = sentitiveId;
    }

    public Boolean getModifiedFlag() {
        return modifiedFlag;
    }

    public void setModifiedFlag(Boolean modifiedFlag) {
        this.modifiedFlag = modifiedFlag;
    }

    public Boolean getViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(Boolean viewFlag) {
        this.viewFlag = viewFlag;
    }
}
