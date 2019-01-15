package com.chejet.cloud.service.impl;

import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.service.VerificationCodeService;
import com.chejet.cloud.vo.SmsVO;
import com.chejet.jmatrix.push.ActionType;
import com.chejet.jmatrix.push.JMatrixPushService;
import com.chejet.jmatrix.push.PushContext;
import com.chejet.jmatrix.push.SmsValidateResult;
import com.chejet.jmatrix.side.SmsSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/11 15:40
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    /**
     * 获取推送Pushservice
     */
    private JMatrixPushService pushService = JMatrixPushService.instance();
    private Logger logger = LoggerFactory.getLogger(VerificationCodeServiceImpl.class);

    @Override
    public SmsValidateResult sendVerificationCode(String mobileNo, String title) {
        //封装推送Model
        PushContext pushContext = PushContext.newBuilder()
                .setActionType(ActionType.PushVerificationCode)
                .setReceiver(mobileNo)
                .setUserId(Long.parseLong(mobileNo))
                .setTitle(title)
                .setBody("10")
                .build();
        //调用Api发送验证码
        SmsValidateResult smsValidateResult = pushService.pushVerificationCode(pushContext);
        if (smsValidateResult == null) {
            logger.info("----------------" + ErrorCodeEnum.MSG_NOT_FOUND.getMessage() + "----------------");
            throw new BaseException(ErrorCodeEnum.MSG_NOT_FOUND);
        }


        return smsValidateResult;
    }

    @Override
    public SmsValidateResult validVerificationCode(Long msgId, String code) {
        SmsValidateResult smsValidateResult = null;
        try {
            smsValidateResult = pushService.validVerificationCode(msgId, code);
            if (smsValidateResult.isStatus()){
                validSmsValidateResult.put(smsValidateResult.getMsgId().toString(), smsValidateResult);
            }
        } catch (Exception e) {
            throw new BaseException(ErrorCodeEnum.USER_MOBILE_VERIFICATION_ERROR);
        }
        return smsValidateResult;
    }

    @Override
    public void pushSmsMessage(String mobileNo, String[] body) {
        PushContext pushContext = PushContext.newBuilder()
                .setActionType(ActionType.PushSMSMessage)
                .setReceiver(mobileNo)
                .setUserId(Long.parseLong(mobileNo))
                .setBody(String.join(",", body))
                .build();
        pushService.pushMessage(pushContext);
    }
}
