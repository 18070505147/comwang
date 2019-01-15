package com.chejet.cloud.filter;

import com.chejet.cloud.common.CookieUtil;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.WebUtil;
import com.chejet.cloud.constant.SymbolicCode;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.memory.ApplicationLocalPermission;
import com.chejet.cloud.memory.UserLocalPermission;
import com.chejet.cloud.rpc.AuthenticationRpcService;
import com.chejet.cloud.rpc.RpcPermission;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Neo.fang
 * @creatTime 2018/11/8 0008
 */
public class PermissionFilter extends ZuulFilter {

    private final PathMatcher pathMatcher = new AntPathMatcher();

    // 存储已获取用户的最新权限，"token+appId+tenantId"为key,权限url为值的集合，当权限发生变动时清空
    private final static ConcurrentHashMap<String, UserLocalPermission> permissionCache = new ConcurrentHashMap<String, UserLocalPermission>();

    // 存储应用在该租户下的最新权限，"appId+tenantId"为key,权限url为值的集合，当权限发生变动时清空
    private final static ConcurrentHashMap<String, ApplicationLocalPermission> applicationPermissionCache = new ConcurrentHashMap<String, ApplicationLocalPermission>();

    private AuthenticationRpcService authenticationRpcService;

    private String[] excludePathPattens;

    public PermissionFilter(AuthenticationRpcService authenticationRpcService, String[] excludePathPattens) {
        this.authenticationRpcService = authenticationRpcService;
        this.excludePathPattens = excludePathPattens;
        Assert.notNull(this.authenticationRpcService, "AuthenticationRpcService init failure");
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {
        // B端与C端的用户可以是同一个？ 如何控制
        // 从请求头那参数信息 appId,tenantId
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Cookie[] cookies = request.getCookies();

        // token没有，不进行权限控制
        String token = WebUtil.getJsonWebToken(request);
        if (StringUtils.isBlank(token)){
            return false;
        }

        //判断是否在过滤url之外, false则不执行
        boolean isExcludedPage;
        if (excludePathPattens != null && excludePathPattens.length != 0) {
            for (String url : excludePathPattens) {
                String servletPath = request.getServletPath();
                if (pathMatcher.match(url, servletPath)) {
                    isExcludedPage = false;
                    return isExcludedPage;
                }
            }
        }

        boolean b = false;
        boolean exApp = false;
        boolean exTenant = false;
        if (cookies != null && cookies.length != 0){
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (!exApp && Objects.equals(cookie.getName(), SymbolicCode.COOKIE_APP_ID)) {
                    String value = cookie.getValue();
                    System.out.println(value);
                    if (!StringUtils.isBlank(value)){
                        exApp = true;
                    }else {
                        exApp = false;
                    }

                }
                if (!exTenant && Objects.equals(cookie.getName(), SymbolicCode.COOKIE_TENANT_ID)) {
                    // String value = cookie.getValue();
                    // System.out.println(value);
                    exTenant = true;
                }
                if (exApp && exTenant) {
                    b = true;
                    break;
                }
            }
        }

        return b;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        try {
            isAccessAllowed(ctx, request);
        } catch (IOException e) {
            // todo
            ctx.setResponseBody("Authentication failed");
        }
        return null;
    }


    public boolean isAccessAllowed(RequestContext ctx, HttpServletRequest request) throws IOException {
        String path = request.getServletPath();
        if (isPermitted(request, path))
            return true;
        else if (!isApplicationPermission(request, path))
            return true;
        else {
            WebUtil.preResponseJson(ctx, ErrorCodeEnum.UESR_PERMISSION_ERROR.getCode(), ErrorCodeEnum.UESR_PERMISSION_ERROR.getMessage());
            return false;
        }
    }

    /**
     * 请求路径是否在用户角色的权限列表中
     */
    private boolean isPermitted(HttpServletRequest request, String path) {
        Set<String> permissionSet = getLocalUserPermission(request);
        if (permissionSet == null || permissionSet.size() == 0) {
            return false;
        } else {
            return permissionSet.contains(path);
        }
    }

    /**
     * 判断是否包含在应用所有权限范围中
     */
    public boolean isApplicationPermission(HttpServletRequest request, String path) {

        Set<String> applicationPermissionSet = getLocalApplicationPermission(request);

        if (applicationPermissionSet == null || applicationPermissionSet.size() == 0) {
            return false;
        } else {
            return applicationPermissionSet.contains(path);
        }
    }

    /**
     * 获取本地存储的用户权限信息
     *
     * @return
     */
    private Set<String> getLocalUserPermission(HttpServletRequest request) {
        // 获取头部参数
        String token = WebUtil.getJsonWebToken(request);
        // 获取cookie参数，后期统一用一种 TODO
        String appId = CookieUtil.getValue(request, SymbolicCode.COOKIE_APP_ID);
        String tenantId = CookieUtil.getValue(request, SymbolicCode.COOKIE_TENANT_ID);
        String companyId = CookieUtil.getValue(request, SymbolicCode.COOKIE_COMPANY_ID);

        // 必要参数校验
        if (StringUtils.isBlank(token) || StringUtils.isBlank(appId) || StringUtils.isBlank(tenantId)) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }

        UserLocalPermission userLocalPermission = permissionCache.get(token + "_" + appId + "_" + tenantId + "_" + companyId);

        if (userLocalPermission == null) {
            userLocalPermission = initUserLocalPermission(request, token, appId, tenantId, companyId);
        }

        return userLocalPermission.getPermissionSet();
    }

    /**
     * 获取本地存储的应用权限信息
     *
     * @return
     */
    private Set<String> getLocalApplicationPermission(HttpServletRequest request) {
        String appId = CookieUtil.getValue(request, SymbolicCode.COOKIE_APP_ID);
        String tenantId = CookieUtil.getValue(request, SymbolicCode.COOKIE_TENANT_ID);
        String companyId = CookieUtil.getValue(request, SymbolicCode.COOKIE_COMPANY_ID);

        if (StringUtils.isBlank(appId) || StringUtils.isBlank(tenantId)) {
            throw new ZuulRuntimeException(new Exception());
        }

        ApplicationLocalPermission applicationLocalPermission = applicationPermissionCache.get(appId + "_" + tenantId + "_" + companyId);

        if (applicationLocalPermission == null) {
            applicationLocalPermission = initApplicationPermission(authenticationRpcService, appId, tenantId, companyId);
        }
        return applicationLocalPermission.getApplicationPermissionSet();
    }


    /**
     * 初始化用户权限信息，基于，一租户一应用一用户的权限
     *
     * @return
     */
    public UserLocalPermission initUserLocalPermission(HttpServletRequest request, String token, String appId, String tenantId, String companyId) {

        // 向权限服务获取权限信息，这里的权限是以一租户一应用一用户的权限，单账号同应用多租户的状况下，采用切换再登录，或一次性存储 TODO
        List<RpcPermission> rpcPermissions = authenticationRpcService.findPermissionList(token, appId, tenantId, companyId);

        //将db数据做本地缓存
        UserLocalPermission userLocalPermission = new UserLocalPermission();

        Set<String> pointUrlSet = new HashSet<>();
        if (rpcPermissions != null && rpcPermissions.size() != 0) {
            for (RpcPermission rpcPermission : rpcPermissions) {
                pointUrlSet.add(rpcPermission.getPointUrl());
            }
        }

        // 保存登录用户没有权限的URL，方便前端去隐藏相应操作按钮
        Set<String> noPermissionSet = new HashSet<String>(getLocalApplicationPermission(request));
        noPermissionSet.removeAll(pointUrlSet);

        userLocalPermission.setPermissionList(rpcPermissions);
        userLocalPermission.setNoPermissions(org.springframework.util.StringUtils.arrayToDelimitedString(noPermissionSet.toArray(), ","));
        userLocalPermission.setPermissionSet(pointUrlSet);

        // 添加权限监控集合，当前session已更新最新权限, key值取用户+appId+tenantId
        permissionCache.put(token + "_" + appId + "_" + tenantId + "_" + companyId, userLocalPermission);

        return userLocalPermission;
    }


    /**
     * 1.初始化应用权限信息，基于，一租户一应用的权限
     * 2.变动通知
     *
     * @return
     */
    public ApplicationLocalPermission initApplicationPermission(AuthenticationRpcService authenticationRpcService, String appId, String tenantId, String companyId) {

        // 获取db中一租户一应用的权限信息
        List<RpcPermission> rpcAppPermissions = authenticationRpcService.findPermissionList(null, appId, tenantId, companyId);

        //将db数据做本地缓存
        ApplicationLocalPermission applicationLocalPermission = new ApplicationLocalPermission();

        Set<String> pointUrlSet = new HashSet<>();
        if (rpcAppPermissions != null && rpcAppPermissions.size() != 0) {
            for (RpcPermission rpcAppPermission : rpcAppPermissions) {
                pointUrlSet.add(rpcAppPermission.getPointUrl());
            }
        }

        applicationLocalPermission.setApplicationRpcPermissionList(rpcAppPermissions);
        applicationLocalPermission.setApplicationPermissionSet(pointUrlSet);

        // 添加权限监控集合，当前应用已更新最新权限, key值取 “appId+tenantId”
        applicationPermissionCache.put(appId + "_" + tenantId + "_" + companyId, applicationLocalPermission);

        return applicationLocalPermission;
    }


    public AuthenticationRpcService getAuthenticationRpcService() {
        return authenticationRpcService;
    }

    public void setAuthenticationRpcService(AuthenticationRpcService authenticationRpcService) {
        this.authenticationRpcService = authenticationRpcService;
    }

    public String[] getExcludePathPattens() {
        return excludePathPattens;
    }

    public void setExcludePathPattens(String[] excludePathPattens) {
        this.excludePathPattens = excludePathPattens;
    }
}
