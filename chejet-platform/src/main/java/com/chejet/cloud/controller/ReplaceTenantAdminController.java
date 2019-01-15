package com.chejet.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.annotation.Slog;
import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.utils.CheckParam;
import com.chejet.cloud.constant.SymbolicCode;
import com.chejet.cloud.exception.BaseException;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.response.ResultBuilder;
import com.chejet.cloud.service.ReplaceTenantAdminService;
import com.chejet.cloud.service.accessApi.TokenManager;
import com.chejet.cloud.vo.RsultMsgVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/12 9:34
 */
@RestController
@RequestMapping(value = "/manager/{tenantId}", produces = "application/json;charset=UTF-8")
public class ReplaceTenantAdminController extends BaseController {
    @Autowired
    private ReplaceTenantAdminService replaceTenantAdminService;

    @Autowired
    private TokenManager tokenManager;


    @Slog
    @PostMapping(value = "/replace")
    public ApiResp replaceTenantAdmin(HttpServletRequest request, @PathVariable Long tenantId,
                                      @RequestBody String payload) {
        JSONObject obj = JSON.parseObject(payload);

        String authHeader = request.getHeader(SymbolicCode.AUTHORIZATION);
        String token = null;
        if (authHeader.startsWith(SymbolicCode.JWT_TOKEN_PRE)) {
            token = authHeader.substring(7);
        }
        if (token == null){
            throw new BaseException(ErrorCodeEnum.USER_TOKEN_ERROR);
        }
        if (obj == null
                || StringUtils.isBlank(obj.getString("mobileNo"))
                || StringUtils.isBlank(obj.getString("oldMobileNo"))
                || StringUtils.isBlank(obj.getString("validMobileNo"))
        ) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        String mobileNo = obj.getString("mobileNo");
        String oldMobileNo = obj.getString("oldMobileNo");
        String validMobileNo = obj.getString("validMobileNo");
        String oldUserId = currentUser().getUserId()+"";
        if (!CheckParam.isMobile(mobileNo)
                && !CheckParam.isMobile(oldMobileNo)
                && !CheckParam.isMobile(validMobileNo)) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        Boolean result = replaceTenantAdminService.replaceTenantAdmin(tenantId, oldMobileNo,validMobileNo, mobileNo,oldUserId);
        // 清除token
        tokenManager.remove(token);
        return ResultBuilder.buildDateSuccess(result);
    }

    @PostMapping(value = "/check")
    public ApiResp checkUserRole(@PathVariable Long tenantId,
                                 @RequestBody String payload) {
        JSONObject obj = JSON.parseObject(payload);
        if (obj == null
                || StringUtils.isBlank(obj.getString("mobileNo"))
        ) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        String mobileNo = obj.getString("mobileNo");
        if (!CheckParam.isMobile(mobileNo)) {
            throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
        }
        RsultMsgVO rsultMsgVO = replaceTenantAdminService.checkUserRole(tenantId, mobileNo);
        return ResultBuilder.buildDateSuccess(rsultMsgVO);
    }
}
