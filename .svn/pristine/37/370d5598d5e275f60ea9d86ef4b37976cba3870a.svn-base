package com.chejet.cloud.exception;

import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.common.ErrorCodeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Neo.fang
 * @creatTime 2018/11/13 0013
 */
public class BaseException extends RuntimeException {

    private final ErrorCodeEnum error;


    /**
     * 私有化，继承自父类构造方法
     * @param ec
     * @param message
     * @param cause
     */
    private BaseException(ErrorCodeEnum ec, String message, Throwable cause) {
        super(message, cause);
        error = ec;
    }

    /**
     * 外部调用的构造方法
     * @param ec
     */
    public BaseException(ErrorCodeEnum ec) {
        this(ec, ec.getMessage(), null);
    }


    public ErrorCodeEnum getError() {
        return error;
    }

    @Override
    public String toString() {
        Map<String, String> jsonObj = new HashMap<String, String>();
        jsonObj.put("code", String.valueOf(error.getCode()));
        jsonObj.put("message", this.getMessage());
        return JSONObject.toJSONString(jsonObj);
    }
}
