package com.chejet.cloud.controller;

import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.utils.JwtUtils;
import com.chejet.cloud.dto.LoginUser;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.po.User;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.response.ResultBuilder;
import com.chejet.cloud.service.accessApi.TokenManager;
import org.apache.commons.lang3.StringUtils;
import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Random
 */
@RestController
@RequestMapping("/logout")
public class LogoutController {
	
	@Resource
	private TokenManager tokenManager;

	@Autowired
	private SQLManager sqlManager;

	@ResponseBody
	@RequestMapping(value = "/off", method = RequestMethod.GET)
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

			user.setLoginStatus(0);
			user.setMtime(new Date());
			sqlManager.updateTemplateById(user);
		}

		if (StringUtils.isNotBlank(token)) {
			tokenManager.remove(token);
		}

		return ResultBuilder.buildDateSuccess(ErrorCodeEnum.SUCCESS);
	}
}