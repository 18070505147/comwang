package com.chejet.cloud.controller;

import com.chejet.cloud.config.JWTHelper;
import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.response.ResultBuilder;
import com.chejet.cloud.service.event.RefreshRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Neo.fang
 * @creatTime 2018/11/5 0005
 */
@RestController
public class RefreshRouteController {

    @Autowired
    private RefreshRouteService refreshRouteService;

    @Autowired
    private ZuulHandlerMapping zuulHandlerMapping;

    @RequestMapping(value = "/refreshRoute")
    public String refreshRoute(){
        refreshRouteService.refreshRoute();
        return "";
    }

    @RequestMapping(value = "/watchNowRoute")
    public ApiResp watchNowRoute(){
        Map<String, Object> handlerMap = zuulHandlerMapping.getHandlerMap();
        return ResultBuilder.buildDateSuccess(handlerMap);
    }

    @RequestMapping(value = "/test")
    public void test(){
        System.out.println(JWTHelper.getPrivateKey());
    }

}
