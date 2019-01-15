package com.chejet.cloud.service.impl;

import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.RoleTypeEnum;
import com.chejet.cloud.constant.SymbolicCode;
import com.chejet.cloud.dto.LoginUser;
import com.chejet.cloud.dto.UserGroup;
import com.chejet.cloud.exception.BizException;
import com.chejet.cloud.service.CurrentUserInfoService;
import com.chejet.cloud.service.accessApi.TokenManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description
 * @Date 2018/12/10 10:59
 * @Version 1.0
 */
@Service
public class CurrentUserInfoServiceImpl implements CurrentUserInfoService {
    @Autowired
    TokenManager tokenManager;
    private Logger logger = LoggerFactory.getLogger(CurrentUserInfoServiceImpl.class);

    @Override
    public CurrentUserInfo currentUser(HttpServletRequest request) {
        //  从Header中获取Authorization的Token信息.
        if (StringUtils.isNotEmpty(request.getHeader(SymbolicCode.AUTHORIZATION))) {
            // 根据token从缓存中获取用户信息

            String authHeader = request.getHeader(SymbolicCode.AUTHORIZATION);
            CurrentUserInfo currentUserInfo = new CurrentUserInfo();

            // 此处直接取token的值，不做校验，因为登录拦截器已经做过校验
            String jwt = authHeader.substring(SymbolicCode.JWT_TOKEN_PRE.length());

            LoginUser loginUser = tokenManager.validate(jwt);
            if (null == loginUser){
                logger.error("未登录或已超时或token无效.token：{}",jwt);
                throw new BizException(ErrorCodeEnum.USER_TOKEN_ERROR);
            }
            List<UserGroup> userGroups = loginUser.getUserGroups();
            currentUserInfo.setUserId(loginUser.getUserId());
            // 优化建议：此处将来可做优化，将tenantId、roleType等信息冗余到loginUser,避免遍历
            for (UserGroup userGroup : userGroups) {
                if (userGroup.isCurrentChoice()) {
                    currentUserInfo.setTenantId(userGroup.getTenantId());
                    currentUserInfo.setRoleType(userGroup.getRoleType());
                }
            }
            // 租户信息不存在
            if (null == currentUserInfo.getTenantId()) {
                logger.error("租户信息不存在.token:{},userId:{},tenantId:{}", jwt, currentUserInfo.getUserId(), currentUserInfo.getTenantId());
                throw new BizException(ErrorCodeEnum.TENANT_INVALID);
            }

            return currentUserInfo;
        }else {
            logger.error("token信息不存在.");
            throw new BizException(ErrorCodeEnum.REQUEST_TOKEN_EMPTY);
        }
        /*
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();

        currentUserInfo.setTenantId(1073414385877123073L);
        currentUserInfo.setUserId(1543973278535L);
        currentUserInfo.setRoleType(RoleTypeEnum.COMPANY_ADMIN.getValue());
        return currentUserInfo;
        */
    }

}
