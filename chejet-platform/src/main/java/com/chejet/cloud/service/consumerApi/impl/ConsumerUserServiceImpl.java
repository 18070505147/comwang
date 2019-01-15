package com.chejet.cloud.service.consumerApi.impl;

import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.dao.UserGroupRoleMapper;
import com.chejet.cloud.dto.LoginUser;
import com.chejet.cloud.dto.UserGroup;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.exception.BizException;
import com.chejet.cloud.po.*;
import com.chejet.cloud.service.VerificationCodeService;
import com.chejet.cloud.service.consumerApi.ConsumerUserService;
import com.chejet.cloud.util.Base64Util;
import com.chejet.cloud.util.PasswordProvider;
import com.chejet.cloud.vo.SecurityVO;
import com.chejet.cloud.vo.UserVO;
import com.chejet.jmatrix.push.SmsValidateResult;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.query.LambdaQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Neo.fang
 * @creatTime 2019/1/8 0008
 */
@Service
public class ConsumerUserServiceImpl implements ConsumerUserService {

    @Autowired
    private SQLManager sqlManager;

    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    private UserGroupRoleMapper userGroupRoleMapper;

    @Override
    public LoginUser login(JSONObject object) {
        String telephone = object.getString("telephone");
        String password = object.getString("password");
        Integer mode = object.getInteger("mode");
        String ip = object.getString("ip");
        String appName = object.getString("appName");
        String appVersion = object.getString("appVersion");

        Date now = new Date();

        // 获取用户信息
        User user = sqlManager.lambdaQuery(User.class).andEq(User::getTelephone, telephone).single();
        if (user == null){
            throw new BaseException(ErrorCodeEnum.USER_INVALID);
        }

        // 判断口令密码是否正确
        boolean isCorrect = false;
        LoginUser loginUser = new LoginUser();
        switch (mode){
            case 1:
                // 密码登录
                Integer passwordLoginErrorCount = checkPasswordLoginErrorCount(user.getId());
                if (Objects.equals(user.getPassword(), PasswordProvider.encrypt(Base64Util.decoder(password)))){
                    isCorrect = true;
                }else {
                    loginUser.setLoginErrorCount(passwordLoginErrorCount + 1);
                }
                break;
            case 2:
                // 短信登录;
                if (StringUtils.isBlank(object.getString("msgId"))){
                    throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
                }
                SmsValidateResult smsValidateResult;
                try {
                    smsValidateResult = verificationCodeService.validVerificationCode(object.getLong("msgId"), password);
                } catch (Exception e) {
                    throw new BaseException(ErrorCodeEnum.MSG_SENDING_EXCEPTION);
                }
                if (smsValidateResult.isStatus() && Objects.equals(String.valueOf(smsValidateResult.getMobile()), telephone)){
                    isCorrect = true;
                }
                break;
            default:
                throw new BaseException(ErrorCodeEnum.ERROR_IN_LOGIN_MODE);
        }

        if (isCorrect){
            if (!Objects.equals(user.getAccountStatus(), 1)){
                throw new BaseException(ErrorCodeEnum.ACCOUNT_DISABLED_OR_CANCELLED);
            }

            // 获取应该信息，并默认设置一个租户和公司
            App app = sqlManager.lambdaQuery(App.class).andEq(App::getName, appName).andEq(App::getAppVersion, appVersion).single();
            if (app == null){
                throw new BaseException(ErrorCodeEnum.APPLICATION_PARAMETERS_NOT_REGISTERED);
            }

            List<UserGroup> userGroups = userGroupRoleMapper.findUserCompanyAppRole(app.getId(), user.getId());
            if (userGroups == null || userGroups.size() == 0){
                throw new BaseException(ErrorCodeEnum.USER_ROLE_INVALID);
            }
            // todo 不选择的情况下默认选中第一个
            userGroups.get(0).setCurrentChoice(true);


            // 更新用户表登录信息, 日志记录
            user.setLoginStatus(1);
            user.setLoginCount(user.getLoginCount() == null ? 1 : user.getLoginCount() + 1);
            user.setLastLoginTime(now);
            user.setLastLoginIp(ip);
            int update = sqlManager.updateTemplateById(user);
            if (update < 1){
                throw new BaseException(ErrorCodeEnum.DB_UPDATE_EXCEPTION);
            }

            // 查询用户头像信息
            String portraitUrl = "";
            if (user.getPortraitUrlId() != null){
                Attach attach = sqlManager.lambdaQuery(Attach.class).andEq(Attach::getId, user.getPortraitUrlId()).single();
                portraitUrl = attach == null ? "" : attach.getUrlName();
            }

            // 返回用户登录信息
            loginUser.setLoginStatus(true);
            loginUser.setUserId(user.getId());
            loginUser.setNickname(user.getNickname());
            loginUser.setModifiedFlag(user.getModifiedFlag());
            loginUser.setLoginTime(now);
            loginUser.setPortraitUrl(portraitUrl);
            loginUser.setAppId(app.getId());
            loginUser.setUserGroups(userGroups);
            loginUser.setTenantId(userGroups.get(0).getTenantId());
            loginUser.setCompanyId(userGroups.get(0).getCompanyId());
        }else {
            // 账号或密码错误
            loginUser.setLoginStatus(false);
        }

        return loginUser;
    }

    @Override
    public boolean register(JSONObject object) {


        return false;
    }

    @Override
    public boolean verificationPhoneNumber(String telephone) {
        User user = sqlManager.lambdaQuery(User.class).andEq(User::getTelephone, telephone).single();
        if (user == null){
            return true;
        }
        return false;
    }

    @Override
    public boolean forgetPassword(JSONObject object) {
        // 再次验证账号
        User user = sqlManager.lambdaQuery(User.class).andEq(User::getTelephone, object.getString("telephone")).single();
        if (user == null){
            throw new BaseException(ErrorCodeEnum.USER_INVALID);
        }
        // 验证短信是否通过 todo 缓存msgId状态，统一一个service
        String msgId = object.getString("msgId");

        // 修改密码
        user.setPassword(PasswordProvider.encrypt(Base64Util.decoder(object.getString("password"))));
        user.setModifiedFlag(false);
        user.setMtime(new Date());
        int update = sqlManager.updateTemplateById(user);
        if (update < 1){
            throw new BaseException(ErrorCodeEnum.DB_UPDATE_EXCEPTION);
        }
        return true;
    }

    @Override
    public boolean modifyPassword(CurrentUserInfo currentUser, SecurityVO securityVo) {
        User user = sqlManager.lambdaQuery(User.class)
                .andEq(User::getId, currentUser.getUserId())
                .single();
        user.setPassword(PasswordProvider.encrypt(Base64Util.decoder(securityVo.getNewPass())));
        user.setModifiedFlag(false);
        user.setMtime(new Date());
        int update = sqlManager.updateTemplateById(user);
        if (update < 1){
            throw new BaseException(ErrorCodeEnum.DB_UPDATE_EXCEPTION);
        }
        return true;
    }

    @Override
    public UserVO getUserInfo(CurrentUserInfo currentUserInfo) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("userId", currentUserInfo.getUserId());
            UserVO userVO = sqlManager.selectSingle("personal.query_info",params,UserVO.class);
            if (StringUtils.isEmpty(userVO.getPortraitUrl())){
                userVO.setPortraitUrlId(1L);
                Attach defaultIcon = sqlManager.lambdaQuery(Attach.class)
                        .andEq(Attach::getId,userVO.getPortraitUrlId())
                        .single();
                userVO.setPortraitUrl(defaultIcon.getUrlName());
            }
            return userVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserVO modifyUserInfo(CurrentUserInfo currentUserInfo, UserVO userVO) {
        try {
            // TODO 校验当前登录人与提交参数是否一致
            User user = new User();
            BeanUtils.copyProperties(userVO, user);
            user.setMtime(new Date());
            user.setId(currentUserInfo.getUserId());
            sqlManager.updateTemplateById(user);
            return userVO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.DB_UPDATE_FAIL);
        }
    }






    /**
     * 获取连续登录次数
     */
    public Integer checkPasswordLoginErrorCount(Long userId){
        // 获取用户最近连续错误密码登录次数(aop是访问结束之后插入日志，不包含本次)
        List<UserLoginLog> loginLogList = sqlManager.lambdaQuery(UserLoginLog.class)
                .andEq(UserLoginLog::getUserId, userId)
                .andEq(UserLoginLog::getLoginMode, 1)
                .andNotEq(UserLoginLog::getLoginStatus, 3)
                .desc(UserLoginLog::getLoginTime).limit(1, 6).select();

        int count = 0;
        Date lastLoginTime = new Date();
        if (loginLogList != null && loginLogList.size() != 0){
            for (UserLoginLog log:loginLogList) {
                if (log.getLoginStatus() == 1){ // 只是密码匹配错误
                    count = count + 1;
                }else {
                    break;
                }
            }
            lastLoginTime = loginLogList.get(0).getLoginTime();
        }
        if (count >= 6 && (new Date().getTime() - lastLoginTime.getTime()) < 1800000){
            throw new BaseException(ErrorCodeEnum.LOCK_YOUR_ACCOUNT_PLEASE_TRY_IT_IN_30_MINUTES);
        }
        return count;
    }
}
