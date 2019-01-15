package com.chejet.cloud.controller;


import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.StaffScaleEnum;
import com.chejet.cloud.constant.OperationConstant;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.exception.BizException;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.service.CurrentUserInfoService;
import com.chejet.cloud.vo.ItemVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;

/**
 * controller层统一处理基类
 * 
 * @Description
 * @Date 2018/12/10 10:53
 * @Version 1.0
 */
@ControllerAdvice
public class BaseController implements OperationConstant {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private HttpServletResponse response;

	@Autowired
	private CurrentUserInfoService currentUserInfoService;
    /**
     * 获取当前登录用户信息
     * @return
     */
    public CurrentUserInfo currentUser(){
        return currentUserInfoService.currentUser(request);
    }
    
    
    public HttpServletResponse getResponse(){
    	return this.response;
    }
    /**
     * 拦截业务异常
     */
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResp notFount(BizException e) {
        log.error("业务异常:{}", e.getMessage());
        return new ApiResp(e.getError().getCode(), e.getMessage());
    }
    
    /**
     * 拦截业务异常
     * 拦截全局异常
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResp notFount(BaseException e) {
        log.error("业务异常:{}", e.getMessage());
        return new ApiResp(e.getError().getCode(), e.getMessage());
    }
    
    /**
     * 拦截业务异常
     * 拦截全局异常
     */
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResp notFount(SQLException e) {
        log.error("业务异常:", e);
		e.printStackTrace();
        return new ApiResp(ErrorCodeEnum.DB_EXCEPTION.getCode(), e.getMessage());
    }

    
    /**
     * 拦截业务异常
     * 拦截全局异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ApiResp notFount(Exception e) {
        log.error("业务异常:", e);
		e.printStackTrace();
        return new ApiResp(e.getMessage());
    }
    
	/**
	 * 拦截未知的运行时异常
	 */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ApiResp notFount(RuntimeException e) {
		log.error("运行时异常:", e);
		e.printStackTrace();
		return new ApiResp(ErrorCodeEnum.SERVER_ERROR.getCode(), ErrorCodeEnum.SERVER_ERROR.getMessage());
	}

	/**
	 * 成功返回封装
	 * 
	 * @param message
	 * @return
	 */
	public ApiResp success(String message) {
		return ApiResp.success(message);
	}

	/**
	 * 失败返回封装
	 * 
	 * @param message
	 * @return
	 */
	public ApiResp error(String message) {
		return ApiResp.failed(message);
	}

	/**
	 * 成功返回封装
	 * 
	 * @param message
	 * @return
	 */
	public <T> ApiResp success(T data, String message) {
		return ApiResp.success(data, message);
	}

	/**
	 * 失败返回封装
	 * 
	 * @param message
	 * @return
	 */
	public <T> ApiResp error(int code, String message) {
		return ApiResp.failed(message, code);
	}
	
	
	public List<ItemVO> getStaffScaleEnum(){
		
		List<ItemVO> list = new ArrayList<ItemVO>();
		for(StaffScaleEnum scalf : StaffScaleEnum.values()){
			ItemVO vo = new ItemVO();
		vo.setId(Long.valueOf(scalf.getValue()));
		vo.setName(scalf.getName());
		list.add(vo);
		}
		return list;
	}

}
