package com.chejet.cloud.controller;

import com.chejet.cloud.response.ApiResp;
import com.chejet.cloud.service.HomePageService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 提供主页相关的接口
 */
@RestController
@RequestMapping(value = "/home_page")
public class HomePageController extends BaseController{

    @Autowired
    HomePageService homePageService;

    /**
     * 首页概览
     * 租户管理员：上线应用数、企业数、应用用户数
     * 企业管理员：企业数、企业员工数、管理应用数
     *
     * @return
     */
    @RequestMapping(value = "/summary", method = RequestMethod.GET)
    public ApiResp summary() {
        return ApiResp.success(homePageService.summary(currentUser()));
    }

    /**
     * 租户应用用户统计
     * @return
     */
    @RequestMapping(value = "/tenant/appuser_statistic", method = RequestMethod.GET)
    public ApiResp statistic() {
        return ApiResp.success(homePageService.statistic(currentUser()));
    }

    /**
     * 租户查看用户登录情况（分页）
     *
     * @return
     */
    @RequestMapping(value = "/tenant/log", method = RequestMethod.GET)
    public ApiResp tenantLog(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "20") Integer pageSize) {
        return ApiResp.success(homePageService.tenantLog(currentUser(),pageNo,pageSize));
    }

    /**
     * 企业员工变动
     *
     * 租户管理员、企业管理员公用
     *
     * @return
     */
    @RequestMapping(value = {"/dept/employee/top"}, method = RequestMethod.GET)
    public ApiResp employeeChange(@RequestParam(name = "num",required = false) Integer num) {
        if (null == num){
            return ApiResp.success(homePageService.employeeChange(currentUser(),5));
        }else {
            return ApiResp.success(homePageService.employeeChange(currentUser(),num));
        }
    }

    /**
     * 企业管理员
     *
     * -企业用户统计
     * @return
     */
    @RequestMapping(value = "/company/user_statistic", method = RequestMethod.GET)
    public ApiResp companyUserStatistic() {
        return ApiResp.success(homePageService.companyUserStatistic(currentUser()));
    }

    /**
     * 企业管理员
     *
     * - 企业员工变动统计
     * @return
     */
    @RequestMapping(value = "/company/employee_change", method = RequestMethod.GET)
    public ApiResp companyEmployeeChange() {
        return ApiResp.success(homePageService.companyEmployeeChange(currentUser()));
    }
    /**
     * 企业管理员
     *
     * -应用列表
     * @return
     */
    @RequestMapping(value = "/company/applist", method = RequestMethod.GET)
    public ApiResp appList() {
        return ApiResp.success(homePageService.queryAPPList(currentUser()));
    }
}