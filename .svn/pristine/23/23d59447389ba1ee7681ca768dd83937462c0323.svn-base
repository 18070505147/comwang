package com.chejet.cloud.service;

import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.vo.SecurityVO;
import com.chejet.cloud.vo.UserVO;

/**
 * @Description
 * @Date 2018/12/5 15:01
 * @Version 1.0
 */
public interface PersonalInfoService {
    /**
     * 查询登录个人信息
     * @param currentUserInfo
     * @return
     */
    UserVO queryInfo(CurrentUserInfo currentUserInfo);

    /**
     * 修改个人资料
     * @param currentUser
     * @param userVO
     * @return
     */
    Boolean modifyInfo(CurrentUserInfo currentUser, UserVO userVO);

    /**
     * 修改密码
     * @param securityVo
     * @return
     */
    Boolean modifyPassword(CurrentUserInfo currentUser,SecurityVO securityVo);

    /**
     * 修改手机号
     * @param currentUserInfo 当前登录用户
     * @param securityVo 修改参数
     * @return
     */
    Boolean modifyTelephone(CurrentUserInfo currentUserInfo, SecurityVO securityVo);

    /**
     * 返回当前登录人的手机号码
     * @param currentUserInfo
     * @return
     */
    UserVO queryTele(CurrentUserInfo currentUserInfo);
}
