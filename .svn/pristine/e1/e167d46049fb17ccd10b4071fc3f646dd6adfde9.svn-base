package com.chejet.cloud.service;

import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.dto.LoginUser;

/**
 * @author Neo.fang
 * @creatTime 2018/12/13 0013
 */
public interface LoginUserService {

    /**
     * 租户登录接口
     */
    LoginUser login(JSONObject object);


    /**
     * 用户注册
     */
    boolean register(JSONObject object);


    /**
     * 手机号唯一校验接口
     */
    boolean verificationPhoneNumber(String telephone);

    /**
     * 忘记密码，通过发短信来修改密码
     */
    boolean forgetPassword(JSONObject object);

}
