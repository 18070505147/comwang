package com.chejet.cloud.common;

import java.util.List;

public enum StaffScaleEnum {

	S020(1, "0~20"), S2199(2, "21~99"), S100199(3, "100~199"), S200500(4, "200~500"), S5011000(5,
			"501~1000"), SMORE1000(6, "1000以上");

	StaffScaleEnum(int value, String name) {
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
