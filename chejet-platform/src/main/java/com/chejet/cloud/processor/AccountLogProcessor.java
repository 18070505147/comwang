package com.chejet.cloud.processor;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.chejet.cloud.config.JWTHelper;
import com.chejet.cloud.po.User;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.util.LongIdGen;
import com.chejet.cloud.util.WebUtil;
import org.beetl.sql.core.SQLManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.po.UserAccountLog;
import com.chejet.cloud.util.StringUtils;

/**
 * 账户日志处理器
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/07
 */
@Component("accountLogProcessor")
public class AccountLogProcessor implements ILogProcessor {

	private static final Logger logger = LoggerFactory.getLogger(OperationLogProcessor.class);

	@Autowired
	private SQLManager sqlManager;

	@Override
	public void process(LogParam logParam) {
		HttpServletRequest request = logParam.getRequest();
		// 请求参数
		// Map<String, String[]> queryParams = request.getParameterMap();
		String param = logParam.getJoinPoint().getArgs()[0].toString();
		JSONObject payload = JSON.parseObject(param);
		// 注解参数
		String description = logParam.getLog().description();
		// 结果
		ApiResp result;
		if (logParam.isException){
			result = JSON.parseObject(logParam.getMessage(), ApiResp.class);
		}else {
			result = (ApiResp) logParam.getObject();
		}

		if (result != null && Objects.equals(200, result.getCode())){
			// 获取当前操作人
			User operationUser = null;
			String authorization = request.getHeader("Authorization");
			if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Bearer ")) {
				String token = authorization.substring(7);
				Map<String,Claim> claims = JWTHelper.parseClaims(token);
				if (claims.keySet().contains("userId")){
					Long userId = claims.get("userId").asLong();
					operationUser = sqlManager.lambdaQuery(User.class).andEq(User::getId, userId).single();
				}
			}

			UserAccountLog accountLog = new UserAccountLog();

			// 新增的时候
			if (payload != null){
				String telephone = payload.getString("telephone");
				User user = sqlManager.lambdaQuery(User.class).andEq(User::getTelephone, telephone).single();
				if (user != null) {
					accountLog.setUserId(user.getId());
					accountLog.setCompanyId(null);
					accountLog.setTenantId(null);
					accountLog.setInitalUsername("");
					accountLog.setModifiedUsername(user.getTelephone());
				}
			}

			// 重置手机时才会有
			if (operationUser != null){
				accountLog.setOperationUser(operationUser.getId().toString());
				if (payload != null){
					String teleNum = payload.getString("teleNum");
					accountLog.setInitalUsername(operationUser.getTelephone());
					accountLog.setModifiedUsername(teleNum);
				}
			}else {
				accountLog.setOperationUser("SYSTEM");
			}


			accountLog.setBusinessType(description == "" ? null : Integer.valueOf(description));
			accountLog.setDeviceInformation(request.getHeader("user-agent"));
			accountLog.setIp(WebUtil.getIpAddress(request));
			accountLog.setCtime(logParam.getBeginTime());
			accountLog.setId(LongIdGen.get().nextId());
			logger.info("执行方法信息:" + JSONObject.toJSON(accountLog));
			sqlManager.insert(accountLog);
		}
	}

}
