package com.chejet.cloud.common;

public enum LogTypeEnum {
	
	/**
	 * 业务操作日志
	 */
	OPERATION(1, "operation"), 
	
	/**
	 * 账户操作日志
	 */
	ACCOUNT(2, "account"),
	
	/**
	 * 用户登录日志
	 */
	LOGIN(3, "login");

	LogTypeEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	private int value;
	private String name;

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
