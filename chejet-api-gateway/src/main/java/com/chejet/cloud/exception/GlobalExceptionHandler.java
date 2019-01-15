package com.chejet.cloud.exception;

import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.response.ResultBuilder;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Neo.fang
 * @creatTime 2018/11/15 0015
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResp resolveException(Exception e) {
        if (e instanceof BaseException) {
            return ResultBuilder.buildCustomFail(((BaseException) e).getError().getCode(), e.getMessage(), null);
//        }else if (e instanceof ZuulException){
//            return ResultBuilder.buildCustomFail(((ZuulException) e).nStatusCode, e.getMessage(), null);
        }else {
            return ResultBuilder.buildCustomFail(ErrorCodeEnum.UNKNOWN_ERROR.getCode(), ErrorCodeEnum.UNKNOWN_ERROR.getMessage(), null);
        }
    }

}
