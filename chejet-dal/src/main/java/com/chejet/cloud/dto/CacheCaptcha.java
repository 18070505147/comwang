package com.chejet.cloud.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Neo.fang
 * @creatTime 2018/10/10 0010
 */
public class CacheCaptcha implements Serializable {

    private static final long serialVersionUID = 1507869346123718356L;

    /**
     * 验证码唯一标识
     */
    private String challenge;

    /**
     * 以字符形式存储的验证码内容，用作校验
     */
    private String captcha;

    /**
     * 验证码状态
     *
     * 0-初始化
     * 1-验证已通过
     */
    private Integer status;

    /**
     * 过期时间
     */
    private Date expired;

    public CacheCaptcha() {
    }

    public CacheCaptcha(String challenge, String captcha) {
        super();
        this.challenge = challenge;
        this.captcha = captcha;
        this.status = 0;
    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }
}
