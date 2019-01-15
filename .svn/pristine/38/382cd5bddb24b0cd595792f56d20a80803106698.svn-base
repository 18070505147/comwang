package com.chejet.cloud.po;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;

/**
 * @Description 首页
 * 企业管理员-企业员工统计
 * 租户管理员-应用员工统计
 * @Date 2018/12/15 15:31
 * @Version 1.0
 */
public class StatisticPO {
    /**
     * 企业ID、应用ID
     */
    @JSONField(serializeUsing = ToStringSerializer.class)
    private Long id;
    /**
     * 企业名称，应用名称
     */
    private String name;

    /**
     * 员工状态 1-在线，0-离线
     */
    private Integer status;
    /**
     * 数量
     */
    private Long num;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
