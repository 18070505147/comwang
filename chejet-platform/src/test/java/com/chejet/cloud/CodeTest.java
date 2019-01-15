package com.chejet.cloud;

import com.chejet.jmatrix.push.ActionType;
import com.chejet.jmatrix.push.JMatrixPushService;
import com.chejet.jmatrix.push.PushContext;
import org.junit.Test;

public class CodeTest {

    @Test
    public void TestCode() {
        //获取推送Pushservice
        JMatrixPushService pushService = JMatrixPushService.instance();
        //封装推送Model
        PushContext pushContext = PushContext.newBuilder()
                .setActionType(ActionType.PushVerificationCode)
                .setReceiver("17610329113")
                .setUserId(123456132156312L)
                .setBody("5")
                .build();
        //调用Api发送验证码
//        Long msgId = pushService.pushVerificationCode(pushContext);
//        System.out.printf("msgid" + msgId);
    }

}
