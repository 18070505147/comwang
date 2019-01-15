package com.chejet.cloud.service.consumerApi;

import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.dto.LoginUser;
import com.chejet.cloud.po.User;
import com.chejet.cloud.vo.SecurityVO;
import com.chejet.cloud.vo.UserVO;

/**
 * @author Neo.fang
 * @creatTime 2019/1/8 0008
 */
public interface ConsumerUserService {

    /**
     * C端普通用户登录
     */
    LoginUser login(JSONObject object);

    /**
     * C端普通用户注册
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

    /**
     * 修改密码，登录用户
     */
    boolean modifyPassword(CurrentUserInfo currentUser,SecurityVO securityVo);

    /**
     * 查询个人资料
     */
    UserVO getUserInfo(CurrentUserInfo currentUserInfo);


    /**
     * 修改个人信息
     */
    UserVO modifyUserInfo(CurrentUserInfo currentUserInfo, UserVO userVO);

}
