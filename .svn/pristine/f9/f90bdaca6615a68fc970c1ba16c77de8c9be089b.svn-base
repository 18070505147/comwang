package com.chejet.cloud.service.captchaApi.impl;

import com.chejet.cloud.dto.CacheCaptcha;
import com.chejet.cloud.service.captchaApi.CaptchaManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Neo.fang
 * @creatTime 2018/10/10 0010
 */
@Service
public class LocalCaptchaManager extends CaptchaManager {

    private final Logger logger = LoggerFactory.getLogger(LocalCaptchaManager.class);

    // 令牌存储结构
    private final ConcurrentHashMap<String, CacheCaptcha> captchaMap = new ConcurrentHashMap<String, CacheCaptcha>();

    @Override
    public void addCaptcha(String captchaId, CacheCaptcha cacheCaptcha) {
        // 这里暂时设为清除过期的缓存验证码
        verifyExpired();
        // 设置1个小时，标识过期时间
        cacheCaptcha.setExpired(new Date(new Date().getTime() + captchaTimeout * 1000));
        cacheCaptcha.setStatus(0);
        captchaMap.put(captchaId, cacheCaptcha);
        logger.debug("CacheCaptcha=ID[" + captchaId +"],Captcha[" + cacheCaptcha.getCaptcha() + "]");
    }

    @Override
    public boolean validate(String captchaId, String captcha) {
        CacheCaptcha cacheCaptcha = captchaMap.get(captchaId);
        if (cacheCaptcha == null) {
            return false;
        }
        //if (Objects.equals(cacheCaptcha.getStatus(), 1)){
        //  return true;
        //}
        if (!captcha.equalsIgnoreCase(cacheCaptcha.getCaptcha())){
            // 校验后删除，前端应刷新
            captchaMap.remove(captchaId);
            return false;
        }else {
            // 改变该验证码状态,并延长时间
            cacheCaptcha.setStatus(1);
            cacheCaptcha.setExpired(new Date(new Date().getTime() + captchaTimeout * 1000));
            captchaMap.put(captchaId, cacheCaptcha);
            return true;
        }
    }

    @Override
    public boolean validateSecond(String captchaId) {
        CacheCaptcha cacheCaptcha = captchaMap.get(captchaId);
        if (cacheCaptcha == null) {
            return false;
        }
        if (Objects.equals(cacheCaptcha.getStatus(), 1)){
            captchaMap.remove(captchaId);
            return true;
        }else {
            captchaMap.remove(captchaId);
            return false;
        }
    }

    @Override
    public void remove(String captchaId) {
        captchaMap.remove(captchaId);
    }


    public void verifyExpired() {
        Date now = new Date();
        for (Map.Entry<String, CacheCaptcha> entry : captchaMap.entrySet()) {
            String captchaId = entry.getKey();
            CacheCaptcha cacheCaptcha = entry.getValue();
            if (cacheCaptcha.getExpired() == null){
                continue;
            }
            // 当前时间大于过期时间
            if (now.compareTo(cacheCaptcha.getExpired()) > 0) {
                // 已过期，清除对应token
                captchaMap.remove(captchaId);
            }
        }
    }
}
