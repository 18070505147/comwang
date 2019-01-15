package com.chejet.cloud.service;


import com.chejet.cloud.vo.SmsVO;
import com.chejet.jmatrix.push.SmsValidateResult;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/11 15:40
 */
public interface VerificationCodeService {

    ConcurrentHashMap<String, SmsValidateResult> validSmsValidateResult = new ConcurrentHashMap<String, SmsValidateResult>();

    SmsValidateResult sendVerificationCode(String mobileNo, String title);

    SmsValidateResult validVerificationCode(Long msgId, String code);

    void pushSmsMessage(String mobileNo, String[] body);
}
