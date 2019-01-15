package com.chejet.cloud.service.captchaApi.impl;

import com.chejet.cloud.dto.CacheCaptcha;
import com.chejet.cloud.service.captchaApi.CaptchaManager;

/**
 * @author Neo.fang
 * @creatTime 2018/12/12 0012
 */
public class RedisCaptchaManager extends CaptchaManager {

    @Override
    public void addCaptcha(String captchaId, CacheCaptcha cacheCaptcha) {

    }

    @Override
    public boolean validate(String captchaId, String captcha) {
        return false;
    }

    @Override
    public boolean validateSecond(String captchaId) {
        return false;
    }

    @Override
    public void remove(String captchaId) {

    }

    @Override
    public void verifyExpired() {

    }
}
