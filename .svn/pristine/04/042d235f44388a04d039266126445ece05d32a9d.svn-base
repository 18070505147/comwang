package com.chejet.cloud.response;

import com.alibaba.fastjson.JSONArray;
import com.chejet.cloud.common.ErrorCodeEnum;

import java.util.List;

/**
 * @author Neo.fang
 * @creatTime 2018/11/6 0006
 */
public class ApiResp<T> {

    private int code;
    private T data;
    private String message;

    public ApiResp(int code, String message){
    	super();
    	this.code = code;
    	this.message = message;
    }
    public ApiResp() {
    }

    public ApiResp(T data) {
        this.code = ErrorCodeEnum.SUCCESS.getCode();
        this.message = ErrorCodeEnum.SUCCESS.getMessage();
        this.data = data;
    }

    /**
     * 生成一个成功的result类
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ApiResp success(T data) {
        ApiResp apiResp = new ApiResp(data);
        apiResp.setCode(ErrorCodeEnum.SUCCESS.getCode());
        apiResp.setMessage(ErrorCodeEnum.SUCCESS.getMessage());
        apiResp.setData(data);
        return apiResp;
    }
    
    public static <T> ApiResp success(T data, String message) {
        ApiResp apiResp = new ApiResp(data);
        apiResp.setCode(ErrorCodeEnum.SUCCESS.getCode());
        apiResp.setMessage(message);
        apiResp.setData(data);
        return apiResp;
    }

    public static ApiResp success(String message) {
        ApiResp apiResp = new ApiResp();
        apiResp.setCode(ErrorCodeEnum.SUCCESS.getCode());
        apiResp.setMessage(message);
        return apiResp;
    }

    public static ApiResp success() {
        ApiResp apiResp = new ApiResp();
        apiResp.setCode(ErrorCodeEnum.SUCCESS.getCode());
        apiResp.setMessage(ErrorCodeEnum.SUCCESS.getMessage());
        return apiResp;
    }

    public static ApiResp failed() {
        ApiResp apiResp = new ApiResp();
        apiResp.setCode(ErrorCodeEnum.FAIL.getCode());
        apiResp.setMessage(ErrorCodeEnum.FAIL.getMessage());
        return apiResp;
    }

    public static <T> ApiResp failed(String message) {
        ApiResp apiResp = new ApiResp();
        apiResp.setCode(ErrorCodeEnum.FAIL.getCode());
        apiResp.setMessage(message);
        return apiResp;
    }

    public static <T> ApiResp failed(List message, int code) {
        ApiResp apiResp = new ApiResp();
        apiResp.setCode(code);
        apiResp.setMessage(JSONArray.toJSONString(message));
        return apiResp;
    }

    public static <T> ApiResp failed(int code) {
        ApiResp apiResp = new ApiResp();
        apiResp.setMessage(ErrorCodeEnum.FAIL.getMessage());
        apiResp.setCode(code);
        return apiResp;
    }

    public static <T> ApiResp failed(Exception e, int code) {
        ApiResp apiResp = new ApiResp();
        apiResp.setMessage(e.getMessage());
        apiResp.setCode(code);
        return apiResp;
    }
    
    public static <T> ApiResp failed(String message, int code) {
        ApiResp apiResp = new ApiResp();
        apiResp.setMessage(message);
        apiResp.setCode(code);
        return apiResp;
    }


    public static <T> ApiResp failed(T data) {
        ApiResp apiResp = new ApiResp();
        apiResp.setMessage(ErrorCodeEnum.FAIL.getMessage());
        apiResp.setCode(ErrorCodeEnum.FAIL.getCode());
        apiResp.setData(data);
        return apiResp;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append(", code=").append(code);
        sb.append(", message=").append(message);
        sb.append(", data=").append(data);     
        sb.append("]");
        return sb.toString();
    }
}
