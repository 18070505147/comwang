package com.chejet.cloud.common;

/**
 * @Description
 * @Date 2018/12/13 14:00
 * @Version 1.0
 */
public enum AppStatusEnum {
    //0-试用中，1-未激活，2-已激活，3-待审核
    TRIAL(0, "试用中"),
    UNACTIVATED(1, "未激活"),
    ACTIVATED(2, "已激活"),
    UNCHECKED(3, "待审核");

    private int value;
    private String name;

    AppStatusEnum(int value, String name) {
        this.name = name;
        this.value = value;
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
