package com.chejet.cloud.common;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Neo.fang
 * @creatTime 2018/12/21 0021
 */
public class HeaderUtil {


    /**
     * 从请求中获取Authorization授权token
     * @param request
     * @return
     */
    public static String getJwtFromRequest(HttpServletRequest request){
        String jwt = null;
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
        }
        return jwt;
    }
}
