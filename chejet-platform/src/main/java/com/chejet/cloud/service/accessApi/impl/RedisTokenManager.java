package com.chejet.cloud.service.accessApi.impl;

import com.chejet.cloud.dto.LoginUser;
import com.chejet.cloud.service.accessApi.TokenManager;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 分布式环境令牌管理
 * @author Random
 */
public class RedisTokenManager extends TokenManager {


	@Override
	public void verifyExpired() {

	}

	@Override
	public void addToken(String token, LoginUser loginUser) {

	}

	@Override
	public void addTokenNoExpired(String token, LoginUser loginUser) {

	}

	@Override
	public LoginUser validate(String token) {
		return null;
	}

	@Override
	public void remove(String token) {

	}

	@Override
	public String getToken(Integer userId) {
		return null;
	}
}
