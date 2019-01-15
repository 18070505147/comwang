package com.chejet.cloud.controller;

import com.chejet.cloud.common.CookieUtil;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.HeaderUtil;
import com.chejet.cloud.common.WebUtil;
import com.chejet.cloud.config.FilterConfig;
import com.chejet.cloud.constant.SymbolicCode;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.filter.PermissionFilter;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.response.ResultBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Neo.fang
 * @creatTime 2018/12/21 0021
 */
@RestController
public class RefreshPermissionController {

    @Autowired
    private PermissionFilter permissionFilter;


    @RequestMapping(value = "/refresh/permission")
    public ApiResp refreshRoute(HttpServletRequest request){
        // 获取token, appId, tenantId 以cookie的形式
        String token = HeaderUtil.getJwtFromRequest(request);
        String appId = CookieUtil.getValue(request, SymbolicCode.COOKIE_APP_ID);
        String tenantId = CookieUtil.getValue(request, SymbolicCode.COOKIE_TENANT_ID);
        String companyId = CookieUtil.getValue(request, SymbolicCode.COOKIE_COMPANY_ID);
        if (StringUtils.isBlank(token) || StringUtils.isBlank(appId) || StringUtils.isBlank(tenantId)){
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }

//        FilterConfig filterConfig = ContextLoader.getCurrentWebApplicationContext().getBean(FilterConfig.class);
        permissionFilter.initApplicationPermission(permissionFilter.getAuthenticationRpcService(), appId, tenantId, companyId);
        permissionFilter.initUserLocalPermission(request, token, appId, tenantId, companyId);
        return ResultBuilder.buildDateSuccess(ErrorCodeEnum.SUCCESS);
    }

}
