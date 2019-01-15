package com.chejet.cloud.common;

import com.chejet.cloud.memory.UserLocalPermission;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Neo.fang
 * @creatTime 2018/11/8 0008
 *
 * 暂时不用session
 */
public class SessionUtil {
	/**
	 * 用户信息
	 */
	public static final String SESSION_USER = "_sessionUser";

	/**
	 * 用户权限
	 */
	public static final String SESSION_USER_PERMISSION = "_sessionUserPermission";


	public static UserLocalPermission getSessionPermission(HttpServletRequest request) {
		return (UserLocalPermission) WebUtils.getSessionAttribute(request, SESSION_USER_PERMISSION);
	}

	public static void setSessionPermission(HttpServletRequest request, UserLocalPermission userLocalPermission) {
		WebUtils.setSessionAttribute(request, SESSION_USER_PERMISSION, userLocalPermission);
	}
	
	public static void invalidate(HttpServletRequest request){
		// setSessionUser(request, null);
		setSessionPermission(request, null);
	}
}