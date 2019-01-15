package com.chejet.cloud.common;

/**
 * @Description 账户状态
 * @Date 2019/1/4 16:23
 * @Version 1.0
 */
public enum AccountStatus {

    /**
     * 注销
     */
    CANCELED(0,"canceled"),
    /**
     * 正常
     */
    NORMAL(1,"normal"),

    /**
     * 锁定
     */
    LOCKED(2,"locked");

    private Integer code;
    private String value;

    AccountStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
