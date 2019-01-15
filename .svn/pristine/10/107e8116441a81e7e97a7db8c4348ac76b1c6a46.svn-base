package com.chejet.cloud.response;

import com.chejet.cloud.common.ErrorCodeEnum;

/**
 * @author Neo.fang
 * @creatTime 2018/11/6 0006
 */
public class ResultBuilder {

    // 创建成功结果
    public static <T> ApiResp buildDateSuccess(T data){
        ApiResp<Object> apiResp = new ApiResp();
        apiResp.setCode(ErrorCodeEnum.SUCCESS.getCode());
        apiResp.setMessage(ErrorCodeEnum.SUCCESS.getMessage());
        if (data == null) {
            apiResp.setData("");
        } else {
            apiResp.setData(data);
        }
        return apiResp;
    }

    // 创建默认code失败结果
    public static ApiResp buildMessageFail(String message){
        ApiResp apiResp = new ApiResp();
        apiResp.setCode(ErrorCodeEnum.FAIL.getCode());
        apiResp.setMessage(message);
        return apiResp;
    }

    // 创建自定义失败结果
    public static ApiResp buildCustomFail(int code, String message, Object arg){
        ApiResp apiResp = new ApiResp();
        apiResp.setCode(code);
        apiResp.setMessage(message);
        apiResp.setData(arg);
        return apiResp;
    }


    
}

