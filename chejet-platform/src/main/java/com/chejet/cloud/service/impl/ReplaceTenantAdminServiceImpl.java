package com.chejet.cloud.service.impl;

import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.RoleTypeEnum;
import com.chejet.cloud.constant.Constants;
import com.chejet.cloud.dao.ReplaceTenantAdminMapper;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.po.*;
import com.chejet.cloud.service.ReplaceTenantAdminService;
import com.chejet.cloud.service.VerificationCodeService;
import com.chejet.cloud.util.PasswordProvider;
import com.chejet.cloud.util.RandomPasswordUtil;
import com.chejet.cloud.util.StringUtils;
import com.chejet.cloud.vo.RsultMsgVO;
import com.chejet.cloud.util.LongIdGen;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/12 9:35
 */
@Service
public class ReplaceTenantAdminServiceImpl implements ReplaceTenantAdminService {
    @Autowired
    private SQLManager sqlManager;
    @Autowired
    private ReplaceTenantAdminMapper replaceTenantAdminMapper;
    @Autowired
    private VerificationCodeService verificationCodeService;

    @Override
    @Transactional
    public Boolean replaceTenantAdmin(Long tenantId, String oldMobileNo, String validMobileNo, String mobileNo, String oldUserId) {
        if (!Objects.equals(validMobileNo, mobileNo)) {
            throw new BaseException(ErrorCodeEnum.PHONE_IS_DIFFERENT);
        }
        User oldUser = sqlManager.lambdaQuery(User.class).andEq(User::getId, oldUserId).single();
        if (!Objects.equals(oldUser.getTelephone(), oldMobileNo)) {
            throw new BaseException(ErrorCodeEnum.PHONE_NOT_CORRECT);
        }
        Date date = new Date();
//        查询当前手机号是否为租户管理员
        String telephone = replaceTenantAdminMapper.findTelephoneByTenantId(tenantId);
        if (!Objects.equals(oldMobileNo, telephone)) {
            throw new BaseException(ErrorCodeEnum.PHONE_NOT_CORRECT);
        }
//  获取租户的租户管理员角色
        GroupAppRole role = sqlManager.lambdaQuery(GroupAppRole.class)
                .andEq(GroupAppRole::getTenantId, tenantId)
                .andEq(GroupAppRole::getRoleType, RoleTypeEnum.TENANT_ADMIN.getValue()).single();
        if (role == null) {
            throw new BaseException(ErrorCodeEnum.ROLES_NOT_FOUND);
        }
        User user = sqlManager.lambdaQuery(User.class).andEq(User::getTelephone, mobileNo).single();
        if (user == null) {
            long userId = LongIdGen.get().nextId();
            user = new User();
            String password = RandomPasswordUtil.genStringRandom(6);
            user.setId(userId);
            user.setTelephone(mobileNo);
            user.setPassword(PasswordProvider.encrypt(password));
            user.setNickname(mobileNo);
            user.setModifiedFlag(true);
//            user.setUsername("0");
            user.setAccountStatus(1);
            user.setLoginCount(0);
            sqlManager.insertTemplate(user);
            // 发送短息
            String[] body = {oldMobileNo, mobileNo, password, Constants.PRODUCT_URL};
            verificationCodeService.pushSmsMessage(mobileNo, body);
        } else if (user.getAccountStatus() == 0) {
// 将账户状态修改为正常
            user.setAccountStatus(1);
            sqlManager.updateTemplateById(user);
        }
        RelUserGroupRole single = sqlManager.lambdaQuery(RelUserGroupRole.class)
                .andEq(RelUserGroupRole::getTenantId, tenantId)
                .andEq(RelUserGroupRole::getUserId, user.getId())
                .andEq(RelUserGroupRole::getRoleId, role.getId())
                .single();
// 判断管理员是不是和将要替换的是同一个账户
        if (single != null) {
            if (Objects.equals(single.getRoleId(), role.getId())) {
                throw new BaseException(ErrorCodeEnum.REPLACE_ADMIN_FIELD);
            }
        }
//        查询替换用户是否兼任当前租户下的企业管理员
        List<RelUserGroupRole> companyAdmin = replaceTenantAdminMapper.getCompanyAdmin(tenantId, user.getId());
        if (companyAdmin != null) {
            companyAdmin.forEach(u -> {
                sqlManager.deleteById(RelUserGroupRole.class, u.getId());
            });
            List<CompanyManager> companyManagers = sqlManager.lambdaQuery(CompanyManager.class).andEq(CompanyManager::getTenantId, tenantId).andEq(CompanyManager::getUserId, user.getId()).select();
            companyManagers.forEach(companyManager -> {
                sqlManager.deleteById(CompanyManager.class, companyManager.getId());
                List<RelManagerDeploymodule> managerDeploymodules = sqlManager.lambdaQuery(RelManagerDeploymodule.class)
                        .andEq(RelManagerDeploymodule::getManagerId, companyManager.getId())
                        .select();
                managerDeploymodules.forEach(deploymodules -> {
                    sqlManager.deleteById(RelManagerDeploymodule.class, deploymodules.getId());
                });
            });
        }
        RelUserGroupRole tenantRoleRel = sqlManager.lambdaQuery(RelUserGroupRole.class)
                .andEq(RelUserGroupRole::getTenantId, tenantId)
                .andEq(RelUserGroupRole::getRoleId, role.getId())
                .andEq(RelUserGroupRole::getUserId, oldUserId).single();
        if (tenantRoleRel == null) {
            throw new BaseException(ErrorCodeEnum.USER_ROLE_INVALID);
        } else {
            RelUserGroupRole relUserGroupRole = new RelUserGroupRole();
            relUserGroupRole.setId(tenantRoleRel.getId());
            relUserGroupRole.setUserId(user.getId());
            relUserGroupRole.setMtime(date);
            sqlManager.updateTemplateById(relUserGroupRole);
        }
        replaceTenantAdminMapper.updateTenantAdmin(tenantId, user.getId());
        return true;
    }

    @Override
    public RsultMsgVO checkUserRole(Long tenantId, String mobileNo) {
        RsultMsgVO rsultMsgVO = new RsultMsgVO();
        User user = sqlManager.lambdaQuery(User.class).andEq(User::getTelephone, mobileNo).single();
        if (user == null) {
            rsultMsgVO.setType(ErrorCodeEnum.ALLOW_REPLACEMENT.getCode());
            rsultMsgVO.setMsg(ErrorCodeEnum.ALLOW_REPLACEMENT.getMessage());
            return rsultMsgVO;
        }
        //获取用户角色列表
        GroupAppRole role = replaceTenantAdminMapper.findUserRoles(tenantId, user.getId());
        if (role == null) {
            rsultMsgVO.setType(ErrorCodeEnum.ALLOW_REPLACEMENT.getCode());
            rsultMsgVO.setMsg(ErrorCodeEnum.ALLOW_REPLACEMENT.getMessage());
            return rsultMsgVO;
        }
        if (Objects.equals(role.getRoleType(), RoleTypeEnum.TENANT_ADMIN.getValue())) {
            rsultMsgVO.setType(ErrorCodeEnum.REPLACE_ADMIN_FIELD.getCode());
            rsultMsgVO.setMsg(ErrorCodeEnum.REPLACE_ADMIN_FIELD.getMessage());
            rsultMsgVO.setRoleId(role.getId() + "");

        } else {
            rsultMsgVO.setType(ErrorCodeEnum.ROLE_CONFLICT.getCode());
            rsultMsgVO.setMsg(ErrorCodeEnum.ROLE_CONFLICT.getMessage());
        }
        return rsultMsgVO;
    }
}
