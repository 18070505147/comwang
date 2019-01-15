package com.chejet.cloud.service;

import com.chejet.cloud.common.CurrentUserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Date 2018/12/10 10:58
 * @Version 1.0
 */
public interface CurrentUserInfoService {
    /**
     * 重当前请求中获取用户信息
     * @param request
     * @return
     */
    CurrentUserInfo currentUser(HttpServletRequest request);
}
