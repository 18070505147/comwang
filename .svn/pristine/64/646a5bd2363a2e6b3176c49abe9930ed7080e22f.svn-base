package com.chejet.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.dto.CacheCaptcha;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.response.ResultBuilder;
import com.chejet.cloud.service.captchaApi.CaptchaHelper;
import com.chejet.cloud.service.captchaApi.CaptchaManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Neo.fang
 * @creatTime 2018/10/10 0010
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Resource
    private CaptchaManager captchaManager;

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void captchaRegister(HttpServletRequest request, HttpServletResponse response, Integer width, Integer height, Integer type, String challenge) throws IOException {
        response.setDateHeader("Expires", 0L);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        if (StringUtils.isBlank(challenge)){
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        CacheCaptcha cacheCaptcha = CaptchaHelper.setInCache(request, response, width, height, type, challenge);
        captchaManager.addCaptcha(cacheCaptcha.getChallenge(),cacheCaptcha);
    }

    @ResponseBody
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public ApiResp captchaValidate(@RequestBody String payload) {
        JSONObject obj = JSON.parseObject(payload);
        if (obj == null
                || StringUtils.isBlank(obj.getString("challenge"))
                || StringUtils.isBlank(obj.getString("captcha"))) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        String challenge = obj.getString("challenge");
        String captcha = obj.getString("captcha");

        boolean validate = captchaManager.validate(challenge, captcha);
        // boolean second = captchaManager.validateSecond(challenge);
        return ResultBuilder.buildDateSuccess(validate);
    }


    @ResponseBody
    @RequestMapping(value = "/validate/second", method = RequestMethod.POST)
    public ApiResp captchaSecondValidate(@RequestBody String payload) {
        JSONObject obj = JSON.parseObject(payload);
        if (obj == null
                || StringUtils.isBlank(obj.getString("challenge"))) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        String challenge = obj.getString("challenge");

        return ResultBuilder.buildDateSuccess(captchaManager.validateSecond(challenge));
    }
}
