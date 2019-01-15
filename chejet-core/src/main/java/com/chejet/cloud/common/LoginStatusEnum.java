package com.chejet.cloud.common;

/**
 * @Description
 * @Date 2019/1/14 9:22
 * @Version 1.0
 */
public enum LoginStatusEnum {
    ONLINE(1,"online"),
    OFFLINE(0,"offline");
    private Integer code;
    private String  value;

    LoginStatusEnum(Integer code, String value) {
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
