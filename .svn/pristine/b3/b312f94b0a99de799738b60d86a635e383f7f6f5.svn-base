package com.chejet.cloud.aop;

import java.lang.reflect.Method;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.processor.*;
import com.chejet.cloud.service.CurrentUserInfoService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.chejet.cloud.annotation.Slog;
import com.chejet.cloud.common.LogTypeEnum;
import com.chejet.cloud.util.SpringUtil;

/**
 * SLog切面实现
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/05
 */
@Aspect
@Component
public class SlogAspect {

	@Autowired(required = false)
	private HttpServletRequest request;

	@Autowired(required = false)
	private HttpServletResponse response;

	@Autowired
	private CurrentUserInfoService currentUserInfoService;

//	@Autowired
//	private SnowflakeIDWorker snowflakeIDWorker;

//	@Pointcut("execution(* com.chejet.cloud.controller..*(..))  ")
	@Pointcut("@annotation(com.chejet.cloud.annotation.Slog)")
	public void aspect() {
	}

	@Around(value = "aspect()")
	public Object validationPoint(ProceedingJoinPoint pjp) throws Throwable {
		Method method = currentMethod(pjp, pjp.getSignature().getName());
		return doHandlerAspect(pjp, method);
	}

	

	/**
	 * 异步线程池处理不同类型日志
	 * 
	 * @param pjp
	 * @param method
	 * @return
	 * @throws Throwable
	 */
	@Async
	private Object doHandlerAspect(ProceedingJoinPoint pjp, Method method) throws Throwable {

		Slog log = method.getAnnotation(Slog.class);

		// 异常日志信息
		String message = null;
		StackTraceElement[] stackTrace = null;
		// 是否执行异常
		boolean isException = false;
		// 开始时间戳
		Date beginTime = new Date();
		Object object = null;
		try {
			object = pjp.proceed(pjp.getArgs());
			return object;
		} catch (Throwable throwable) {
			isException = true;
			message = throwable.toString();
			stackTrace = throwable.getStackTrace();
			throw throwable;
		} finally {

			// 日志处理
			logHandle(pjp, method, log, beginTime, isException, message, object);
		}
	}
	
	protected Method currentMethod(ProceedingJoinPoint joinPoint, String methodName) {
		Method[] methods = joinPoint.getTarget().getClass().getMethods();
		Method resultMethod = null;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				resultMethod = method;
				break;
			}
		}
		return resultMethod;
	}

	private void logHandle(ProceedingJoinPoint joinPoint, Method method, Slog log, Date beginTime, boolean isException,
						   String message, Object object) {
		
		if(log==null){
			return;
		}
		
		LogParam logParam = new LogParam();
		logParam.setJoinPoint(joinPoint);
		logParam.setMethod(method);
		logParam.setLog(log);
		logParam.setBeginTime(beginTime);
		logParam.setException(isException);
		logParam.setMessage(message);
		logParam.setObject(object);
		logParam.setRequest(request);
		logParam.setResponse(response);

        ILogProcessor logProcessor = null;
        if (log.type().equals(LogTypeEnum.OPERATION.getName())) {
            CurrentUserInfo currentUserInfo = currentUserInfoService.currentUser(request);
            if (null == currentUserInfo){
                logParam.setUserId(null);
            }else {
                logParam.setUserId(currentUserInfo.getUserId());
            }
            logProcessor = SpringUtil.getBean(OperationLogProcessor.class);
		}

		if (log.type().equals(LogTypeEnum.ACCOUNT.getName())) {
			logProcessor = SpringUtil.getBean(AccountLogProcessor.class);
		}

		if (log.type().equals(LogTypeEnum.LOGIN.getName())) {
			logProcessor = SpringUtil.getBean(LoginLogProcessor.class);
		}

		logProcessor.process(logParam);
	}

}
