package com.chejet.cloud.controller;

import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.service.PersonalInfoService;
import com.chejet.cloud.vo.SecurityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Date 2018/12/14 9:29
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/security")
public class SecurityController extends BaseController{

    @Autowired
    private PersonalInfoService personalInfoService;

    @RequestMapping(value = "/password/modify")
    public ApiResp<Boolean> modifyPassword(@RequestBody SecurityVO securityVo){
        if (personalInfoService.modifyPassword(currentUser(), securityVo)){
            return ApiResp.success(true);
        }
        return ApiResp.failed(false);
    }

    @RequestMapping(value = "/tele/modify")
    public ApiResp<Boolean> modifyTelephone(@RequestBody SecurityVO securityVO){
        if (personalInfoService.modifyTelephone(currentUser(),securityVO)){
            return ApiResp.success(true);
        }
        return ApiResp.failed(false);
    }
}
