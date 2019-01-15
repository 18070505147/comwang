package com.chejet.cloud.service.hessianRpcApi.impl;

import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.dao.FunctionPermissionPointMapper;
import com.chejet.cloud.dto.LoginUser;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.po.RelUserGroupRole;
import com.chejet.cloud.service.accessApi.TokenManager;
import com.chejet.cloud.service.hessianRpcApi.AuthenticationRpcService;
import com.chejet.cloud.dto.RpcPermission;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.query.LambdaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Neo.fang
 * @creatTime 2018/12/18 0018
 */
@Service
public class AuthenticationRpcServiceImpl implements AuthenticationRpcService {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private FunctionPermissionPointMapper functionPermissionPointMapper;

    @Override
    public boolean validate(String token) {
        return tokenManager.validate(token) != null;
    }

    @Override
    public List<RpcPermission> findPermissionList(String token, String app, String tenant, String company) {
        // 参数转换与控制
        if (StringUtils.isBlank(app) || StringUtils.isBlank(tenant)) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        Long appId = Long.valueOf(app);
        Long tenantId = Long.valueOf(tenant);
        Long companyId = null;
        if (!StringUtils.isBlank(company)){
            companyId = Long.valueOf(company);
        }

        // 在租户(公司)下查找app的所有功能和权限点
        if (StringUtils.isBlank(token)) {
            return findAppPersonalPermissionList(null, appId, tenantId, companyId);
        } else {
            LoginUser user = tokenManager.validate(token);
            if (user != null) {
                return findAppPersonalPermissionList(user.getUserId(), appId, tenantId, companyId);
            } else {
                return new ArrayList<RpcPermission>(0);
            }
        }
    }


    public List<RpcPermission> findAppPersonalPermissionList(Long userId, Long appId, Long tenantId, Long companyId) {
        List<RpcPermission> appPersonalPermissionList = functionPermissionPointMapper.findAppPersonalPermissionList(userId, appId, tenantId, companyId);
        return appPersonalPermissionList;
    }

}
