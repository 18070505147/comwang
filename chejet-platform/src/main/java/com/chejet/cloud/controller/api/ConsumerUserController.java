package com.chejet.cloud.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.annotation.Slog;
import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.utils.CheckParam;
import com.chejet.cloud.common.utils.JwtUtils;
import com.chejet.cloud.config.JWTHelper;
import com.chejet.cloud.constant.SymbolicCode;
import com.chejet.cloud.controller.BaseController;
import com.chejet.cloud.dto.LoginUser;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.exception.BizException;
import com.chejet.cloud.po.User;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.response.ResultBuilder;
import com.chejet.cloud.service.accessApi.TokenManager;
import com.chejet.cloud.service.consumerApi.ConsumerUserService;
import com.chejet.cloud.vo.SecurityVO;
import com.chejet.cloud.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;

/**
 * @author Neo.fang
 * @creatTime 2019/1/8 0008
 */
@RestController
@RequestMapping(value = "/consumer", produces = "application/json;charset=UTF-8")
public class ConsumerUserController extends BaseController {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private ConsumerUserService consumerUserService;

    @Autowired
    private SQLManager sqlManager;

    @Slog(modelName = "产品用户登录", description = "执行用户登录操作", type = "login")
    @RequestMapping(value = "/login/sign_in", method = RequestMethod.POST)
    public ApiResp login(@RequestBody String payload, HttpServletRequest request, HttpServletResponse response) {
        JSONObject obj = JSON.parseObject(payload);
        if (obj == null
                || StringUtils.isBlank(obj.getString("telephone"))
                || StringUtils.isBlank(obj.getString("mode"))
                || StringUtils.isBlank(obj.getString("password"))
                || !CheckParam.isMobile(obj.getString("telephone"))
                || StringUtils.isBlank(obj.getString("ip"))
                || StringUtils.isBlank(obj.getString("deviceInfo"))
                || StringUtils.isBlank(obj.getString("loginDevice"))
                || StringUtils.isBlank(obj.getString("appName"))
                || StringUtils.isBlank(obj.getString("appVersion"))){
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        // todo  应用信息如何携带（主控台下应为租户列表及某一租户下的应用列表，点击某一应用，获取应用下的角色权限）
        // 主控台做权限，主控台应为一个应用控件，具体应用信息由前端配置
        LoginUser loginUser = consumerUserService.login(obj);

        if (loginUser.isLoginStatus()){
            // token
            String token = JwtUtils.getJwtFromRequest(request);
            loginUser.setUserToken("Bearer " + token);
            if (StringUtils.isBlank(token) || tokenManager.validate(token) == null) {// 没有登录的情况
                token = JWTHelper.createJwt(loginUser.getUserId(), loginUser.getAppId(), obj.getString("deviceInfo"));
                // 此处修改为，带参是否去除token的时间校验
                if (!Objects.equals(obj.getInteger("keepLogin"),1)){
                    tokenManager.addToken(token, loginUser);
                }else {
                    tokenManager.addTokenNoExpired(token, loginUser);
                }
                loginUser.setUserToken("Bearer " + token);
                response.addHeader("Authorization", "Bearer " + token);
            }
            return ResultBuilder.buildDateSuccess(loginUser);
        }else {
            if (obj.getInteger("mode") == 1){
                return ResultBuilder.buildCustomFail(ErrorCodeEnum.USER_USERNAME_PASSWORD_INVALID.getCode(),ErrorCodeEnum.USER_USERNAME_PASSWORD_INVALID.getMessage(), loginUser);
            }else {
                return ResultBuilder.buildCustomFail(ErrorCodeEnum.USER_MOBILE_VERIFICATION_ERROR.getCode(),ErrorCodeEnum.USER_MOBILE_VERIFICATION_ERROR.getMessage(), loginUser);
            }
        }
    }

    @Slog(modelName = "用户注册", description = "1", type = "account")
    @RequestMapping(value = "/register/sign_up", method = RequestMethod.POST)
    public ApiResp register(@RequestBody String payload){
        JSONObject obj = JSON.parseObject(payload);
        if (obj == null
                || StringUtils.isBlank(obj.getString("telephone"))
                // || StringUtils.isBlank(obj.getString("nickname"))
                || StringUtils.isBlank(obj.getString("password"))
                || StringUtils.isBlank(obj.getString("msgId"))){
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        return ResultBuilder.buildDateSuccess(consumerUserService.register(obj));
    }


    @RequestMapping(value = "/verification", method = RequestMethod.GET)
    public ApiResp verificationTelephone(String telephone) {
        if (StringUtils.isBlank(telephone)){
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        if (!CheckParam.isMobile(telephone)){
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        return ResultBuilder.buildDateSuccess(consumerUserService.verificationPhoneNumber(telephone));
    }

    @RequestMapping(value = "/forget/password", method = RequestMethod.POST)
    public ApiResp forgetPassword(@RequestBody String payload){
        JSONObject obj = JSON.parseObject(payload);
        if (obj == null
                || StringUtils.isBlank(obj.getString("telephone"))
                || StringUtils.isBlank(obj.getString("msgId"))
                || StringUtils.isBlank(obj.getString("password"))
                || !CheckParam.isMobile(obj.getString("telephone"))){
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        return ResultBuilder.buildDateSuccess(consumerUserService.forgetPassword(obj));
    }

    @RequestMapping(value = "/password/modify")
    public ApiResp<Boolean> modifyPassword(HttpServletRequest request, @RequestBody SecurityVO securityVo){

        String authHeader = request.getHeader(SymbolicCode.AUTHORIZATION);
        if (authHeader == null){
            throw new BaseException(ErrorCodeEnum.USER_TOKEN_ERROR);
        }
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        String jwt = authHeader.substring(SymbolicCode.JWT_TOKEN_PRE.length());
        LoginUser loginUser = tokenManager.validate(jwt);
        if (null == loginUser){
            throw new BaseException(ErrorCodeEnum.USER_TOKEN_ERROR);
        }
        currentUserInfo.setUserId(loginUser.getUserId());

        if (consumerUserService.modifyPassword(currentUserInfo, securityVo)){
            return ApiResp.success(true);
        }
        return ApiResp.failed(false);
    }

    @RequestMapping(value = "/personalInfo", method = RequestMethod.GET)
    public ApiResp getUserInfo(HttpServletRequest request) {

        String authHeader = request.getHeader(SymbolicCode.AUTHORIZATION);
        if (authHeader == null){
            throw new BaseException(ErrorCodeEnum.USER_TOKEN_ERROR);
        }
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        String jwt = authHeader.substring(SymbolicCode.JWT_TOKEN_PRE.length());
        LoginUser loginUser = tokenManager.validate(jwt);
        if (null == loginUser){
            throw new BaseException(ErrorCodeEnum.USER_TOKEN_ERROR);
        }
        currentUserInfo.setUserId(loginUser.getUserId());

        return ResultBuilder.buildDateSuccess(consumerUserService.getUserInfo(currentUserInfo));
    }

    @RequestMapping(value = "/personalInfo/edit", method = RequestMethod.POST)
    public ApiResp modifyPersonalInfo(HttpServletRequest request, @RequestBody UserVO userVO) {

        String authHeader = request.getHeader(SymbolicCode.AUTHORIZATION);
        if (authHeader == null){
            throw new BaseException(ErrorCodeEnum.USER_TOKEN_ERROR);
        }
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        String jwt = authHeader.substring(SymbolicCode.JWT_TOKEN_PRE.length());
        LoginUser loginUser = tokenManager.validate(jwt);
        if (null == loginUser){
            throw new BaseException(ErrorCodeEnum.USER_TOKEN_ERROR);
        }
        currentUserInfo.setUserId(loginUser.getUserId());

        return ApiResp.success(consumerUserService.modifyUserInfo(currentUserInfo, userVO));
    }

    @RequestMapping(value = "/logout/off", method = RequestMethod.GET)
    public ApiResp logout(HttpServletRequest request) {
        String token = JwtUtils.getJwtFromRequest(request);

        if (StringUtils.isBlank(token) || tokenManager.validate(token) == null){
            throw new BaseException(ErrorCodeEnum.USER_TOKEN_ERROR);
        }
        // 获取用户并修改用户状态为下线
        LoginUser loginUser = tokenManager.validate(token);
        User user = null;
        if (loginUser != null) {
            user = sqlManager.lambdaQuery(User.class).andEq(User::getId, loginUser.getUserId()).single();
            user.setMtime(new Date());
            user.setLoginStatus(0);
            sqlManager.updateTemplateById(user);
        }

        if (StringUtils.isNotBlank(token)) {
            tokenManager.remove(token);
        }

        return ResultBuilder.buildDateSuccess(ErrorCodeEnum.SUCCESS);
    }

}
