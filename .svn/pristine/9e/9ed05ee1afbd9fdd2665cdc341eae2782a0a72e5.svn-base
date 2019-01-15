package com.chejet.cloud.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Description
 * @Date 2018/12/12 13:59
 * @Version 1.0
 */
public class LoginLogVO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 应用名
     */
    private String appName;

    /**
     * 登录时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

    /**
     * 登录端
     */
    private Integer loginDevice;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getLoginDevice() {
        return loginDevice;
    }

    public void setLoginDevice(Integer loginDevice) {
        this.loginDevice = loginDevice;
    }
}
