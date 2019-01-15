package com.chejet.cloud.vo;

import java.beans.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/14
 */
public class Page {
	@JsonIgnore
	public Integer pageSize = 20;
	@JsonIgnore
	public Integer pageNumber = 1;
	@JsonIgnore
	String orderBy;
	@Transient
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	@Transient
	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	@Transient
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
