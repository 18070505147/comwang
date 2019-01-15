package com.chejet.cloud.service.captchaApi;

import com.chejet.cloud.dto.CacheCaptcha;

/**
 * 验证码管理
 *
 * @author Neo.fang
 * @creatTime 2018/12/12 0012
 */
public abstract class CaptchaManager {

    protected int captchaTimeout = 3600;

    /**
     * 验证码生成成功后将授权信息存入
     *
     * @param captchaId
     * @param cacheCaptcha
     */
    public abstract void addCaptcha(String captchaId, CacheCaptcha cacheCaptcha);

    /**
     * 验证验证码有效性
     *
     * @param captchaId
     * @return
     */
    public abstract boolean validate(String captchaId, String captcha);

    /**
     * 验证验二次证码有效性
     *
     * @param captchaId
     * @return
     */
    public abstract boolean validateSecond(String captchaId);

    /**
     * 删除验证码
     *
     * @param captchaId
     */
    public abstract void remove(String captchaId);


    /**
     * 验证失效的验证码
     */
    public abstract void verifyExpired();



    public void setCaptchaTimeout(int captchaTimeout) {
        this.captchaTimeout = captchaTimeout;
    }

}
