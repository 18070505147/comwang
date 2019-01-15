package com.chejet.cloud.vo;

import com.chejet.cloud.po.AppFunction;

import java.util.List;

/**
 * @Description
 * @Date 2018/12/20 13:52
 * @Version 1.0
 */
public class DeployModuleVO {
    private Long id;

    /**
     * 配置包名称
     */
    private String name;

    /**
     * app编码 关联平台应用表
     */
    private Long appId;

    /**
     * 是否已选中
     */
    private Boolean checked;
    private List<AppFunction> functionList;

    public List<AppFunction> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<AppFunction> functionList) {
        this.functionList = functionList;
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

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
