package com.chejet.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.utils.CheckParam;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.response.ResultBuilder;
import com.chejet.cloud.service.VerificationCodeService;
import com.chejet.cloud.vo.SmsVO;
import com.chejet.jmatrix.push.SmsValidateResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/11 15:39
 */
@RestController
@RequestMapping(value = "/sms", produces = "application/json;charset=UTF-8")
public class VerificationCodeController {
    @Autowired
    private VerificationCodeService verificationCodeService;

    @PostMapping(value = "/sendMsg")
    public ApiResp sendVerificationCode(@RequestBody String payload) {
        JSONObject obj = JSON.parseObject(payload);
        if (obj == null
                || StringUtils.isBlank(obj.getString("mobileNo"))
                || StringUtils.isBlank(obj.getString("title"))
        ) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        String mobileNo = obj.getString("mobileNo");
        String title = obj.getString("title");
        if (!CheckParam.isMobile(mobileNo)) {
            return ResultBuilder.buildMessageFail(ErrorCodeEnum.PARAM_ERROR.getMessage());
        }
        SmsValidateResult smsValidateResult;
        smsValidateResult = verificationCodeService.sendVerificationCode(mobileNo, title);
        return ResultBuilder.buildDateSuccess(smsValidateResult);
    }

    @PostMapping(value = "/validMsg")
    public ApiResp validVerificationCode(@RequestBody String payload) {
        JSONObject obj = JSON.parseObject(payload);
        if (obj == null
                || StringUtils.isBlank(obj.getString("msgCode"))
                || StringUtils.isBlank(obj.getString("msgId"))
        ) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        String msgCode = obj.getString("msgCode");
        Long msgId = Long.parseLong(obj.getString("msgId"));
        SmsValidateResult smsValidateResult;
        try {
            smsValidateResult = verificationCodeService.validVerificationCode(msgId, msgCode);
        } catch (Exception e) {
            throw new BaseException(ErrorCodeEnum.MSG_SENDING_EXCEPTION);
        }
        return ResultBuilder.buildDateSuccess(smsValidateResult);
    }
}
