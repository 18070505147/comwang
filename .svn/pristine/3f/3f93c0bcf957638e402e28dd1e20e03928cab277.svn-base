package com.chejet.cloud.common;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/18 15:11
 * @desc: 修改可能会引起短信验证码的非正常工作，请勿擅自修改，如有需要请联系本人。
 */
public enum SMSModelEnum {
    /**
     * 短信验证码模板
     */
    REGISTRATION_VERIFICATION_TEMPLATE(1000, "注册模板"),
    LOGIN_VERIFICATION_TEMPLATE(1001, "登录模板"),
    RESET_PHONE_VERIFICATION_TEMPLATE(1002, "重置手机"),
    CHANGE_PASSWORD_VERIFICATION_TEMPLATE(1003, "修改密码"),
    LOGOUT_VERIFICATION_TEMPLATE(1004, "注销账号"),
    REPLACE_VERIFICATION_TEMPLATE(1005, "更换租户管理员");
    private int value;
    private String name;

    SMSModelEnum(int value, String name) {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
