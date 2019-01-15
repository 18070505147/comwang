package com.chejet.cloud.processor;

import java.lang.reflect.Method;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import com.chejet.cloud.annotation.Slog;

/**
 * 日志参数封装类
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/07
 */
public class LogParam {
	private ProceedingJoinPoint joinPoint;
	private Method method;
	private Slog log;
	private Date beginTime;
	boolean isException;
	private String message;
	private Throwable throwable;
	private Object object;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Long userId;

	public ProceedingJoinPoint getJoinPoint() {
		return joinPoint;
	}

	public void setJoinPoint(ProceedingJoinPoint joinPoint) {
		this.joinPoint = joinPoint;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Slog getLog() {
		return log;
	}

	public void setLog(Slog log) {
		this.log = log;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public boolean isException() {
		return isException;
	}

	public void setException(boolean isException) {
		this.isException = isException;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
