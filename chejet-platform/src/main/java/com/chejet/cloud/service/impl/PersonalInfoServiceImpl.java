package com.chejet.cloud.service.impl;

import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.LoginStatusEnum;
import com.chejet.cloud.exception.BizException;
import com.chejet.cloud.po.Attach;
import com.chejet.cloud.po.User;
import com.chejet.cloud.po.UserAccountLog;
import com.chejet.cloud.service.PersonalInfoService;
import com.chejet.cloud.util.Base64Util;
import com.chejet.cloud.util.LongIdGen;
import com.chejet.cloud.util.PasswordProvider;
import com.chejet.cloud.vo.SecurityVO;
import com.chejet.cloud.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.hibernate.validator.constraints.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 个人信息管理
 * @Date 2018/12/5 15:05
 * @Version 1.0
 */
@Component
public class PersonalInfoServiceImpl implements PersonalInfoService {
    private Logger logger = LoggerFactory.getLogger(PersonalInfoServiceImpl.class);
    @Autowired
    private SQLManager sqlManager;

    /**
     * 查询个人资料信息
     *
     * @param currentUserInfo 当前登录用户
     * @return
     */
    @Override
    public UserVO queryInfo(CurrentUserInfo currentUserInfo) {
        try {
//            User user = sqlManager.lambdaQuery(User.class)
//                    .andEq(User::getId, currentUserInfo.getUserId())
//                    .single();
//            UserVO userVO = new UserVO();
//            BeanUtils.copyProperties(user, userVO);
            Map<String, Object> params = new HashMap<>();
            params.put("userId", currentUserInfo.getUserId());
            UserVO userVO = sqlManager.selectSingle("personal.query_info", params, UserVO.class);
            if (StringUtils.isEmpty(userVO.getPortraitUrl())) {
                userVO.setPortraitUrlId(1L);
                Attach defaultIcon = sqlManager.lambdaQuery(Attach.class)
                        .andEq(Attach::getId, userVO.getPortraitUrlId())
                        .single();
                userVO.setPortraitUrl(defaultIcon.getUrlName());
            }
            return userVO;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改个人资料信息
     *
     * @param currentUser 当前登录用户
     * @param userVO      修改后信息
     * @return
     */
    @Override
    public Boolean modifyInfo(CurrentUserInfo currentUser, UserVO userVO) {
        try {
            // TODO 校验当前登录人与提交参数是否一致
            User user = new User();
            BeanUtils.copyProperties(userVO, user);
            user.setMtime(new Date());
            user.setId(currentUser.getUserId());
//            sqlManager.updateTemplateById(user); //该方法会忽略空值
            sqlManager.update("personal.update_personal_info", user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.DB_UPDATE_FAIL);
        }
    }

    /**
     * 修改个人密码
     *
     * @param currentUser 当前登录用户
     * @param securityVo  密码
     * @return
     */
    @Override
    public Boolean modifyPassword(CurrentUserInfo currentUser, SecurityVO securityVo) {
        try {
            User userinfo = sqlManager.lambdaQuery(User.class)
                    .andEq(User::getId, currentUser.getUserId())
                    .single();
            String encryptPass = PasswordProvider.encrypt(Base64Util.decoder(securityVo.getOldPass()));
            // 判断旧密码
            if (!userinfo.getPassword().equals(encryptPass)) {
                logger.error("旧密码错误!");
                throw new BizException(ErrorCodeEnum.OLD_PASSWORD_INVALID);
            }
            userinfo.setPassword(PasswordProvider.encrypt(Base64Util.decoder(securityVo.getNewPass())));
            userinfo.setModifiedFlag(false);
            userinfo.setMtime(new Date());
            sqlManager.updateTemplateById(userinfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 修改手机号码
     *
     * @param currentUserInfo 当前登录用户
     * @param securityVo      手机号码
     * @return
     */
    @Override
    public Boolean modifyTelephone(CurrentUserInfo currentUserInfo, SecurityVO securityVo) {
        try {
            if (StringUtils.isNotEmpty(securityVo.getTeleNum())) {
                // 校验手机号是否已被注册
                List<User> existUsers = sqlManager.lambdaQuery(User.class)
                .andEq(User::getTelephone,securityVo.getTeleNum())
                .select();
                if (existUsers.size() > 0){
                    logger.error("手机号{}已被注册，请更换手机号或者联系管理员!",securityVo.getTeleNum());
                    throw new BizException(ErrorCodeEnum.TELEPHONE_EXIST);
                }
                User userinfo = sqlManager.lambdaQuery(User.class)
                        .andEq(User::getId, currentUserInfo.getUserId())
                        .single();
                userinfo.setTelephone(securityVo.getTeleNum());
                userinfo.setLoginStatus(LoginStatusEnum.OFFLINE.getCode());
                userinfo.setMtime(new Date());

                // 用户变更日志
                UserAccountLog userAccountLog = new UserAccountLog();
                userAccountLog.setId(LongIdGen.get().nextId());
                userAccountLog.setUserId(currentUserInfo.getUserId());
                userAccountLog.setBusinessType(1);
                userAccountLog.setCtime(new Date());
                userAccountLog.setOperationUser(currentUserInfo.getUserId().toString());

                sqlManager.updateTemplateById(userinfo);
                sqlManager.insert(userAccountLog);


                return true;
            } else {

                throw new BizException(ErrorCodeEnum.PARAM_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.BUSINESS_ERROR);
        }
    }

    /**
     * 查询当前登录人的手机号码
     *
     * @param currentUserInfo
     * @return
     */
    @Override
    public UserVO queryTele(CurrentUserInfo currentUserInfo) {
        try {
            User user = sqlManager.lambdaQuery(User.class)
                    .andEq(User::getId, currentUserInfo.getUserId())
                    .single();
            UserVO userVO = new UserVO();
            if (null != user) {
                userVO.setTelephone(user.getTelephone());
                return userVO;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.DB_SELECT_EXCEPTION);
        }
    }
}
