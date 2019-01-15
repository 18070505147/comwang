package com.chejet.cloud.common.utils;

import com.auth0.jwt.interfaces.Claim;
import com.chejet.cloud.config.JWTHelper;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * JWT工具
 * @author Jason
 */
public class JwtUtils {
    public static String USER_ID = "userId";
    public static String ROLE_ID = "roleId";
    public static String TENANT_ID = "tanentId";
    public static String NAME = "name";

    public static Long getUserId(String token){
        if (token == null){
            return null;
        }
        Map<String,Claim> claims = JWTHelper.parseClaims(token);
        if (claims.keySet().contains(USER_ID)){
            return claims.get(USER_ID).asLong();
        }
        return null;
    }

    public static Long getTanentId(String token){
        if (token == null){
            return null;
        }
        Map<String,Claim> claims = JWTHelper.parseClaims(token);
        if (claims.keySet().contains(TENANT_ID)){
            return claims.get(TENANT_ID).asLong();
        }
        return null;
    }

    public static Long getRoleId(String token){
        if (token == null){
            return null;
        }
        Map<String,Claim> claims = JWTHelper.parseClaims(token);
        if (claims.keySet().contains(ROLE_ID)){
            return claims.get(ROLE_ID).asLong();
        }
        return null;
    }

    public static String getName(String token){
        if (token == null){
            return null;
        }
        Map<String,Claim> claims = JWTHelper.parseClaims(token);
        if (claims.keySet().contains(NAME)){
            return claims.get(NAME).asString();
        }
        return null;
    }

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
