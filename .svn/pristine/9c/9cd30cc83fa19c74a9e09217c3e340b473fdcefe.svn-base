package com.chejet.cloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.common.RoleTypeEnum;
import com.chejet.cloud.service.HomePageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomePageServiceImplTest {

    @Autowired
    HomePageService homePageService;

    @Test
    public void summary() {
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        currentUserInfo.setUserId(1543973278535L);
        currentUserInfo.setTenantId(2L);
        currentUserInfo.setRoleType(RoleTypeEnum.TENANT_ADMIN.getValue());

        System.out.println(JSONObject.toJSONString(homePageService.summary(currentUserInfo)));
    }

    @Test
    public void employeeChange() {
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        currentUserInfo.setUserId(1543973278535L);
        currentUserInfo.setTenantId(2L);
        currentUserInfo.setRoleType(RoleTypeEnum.TENANT_ADMIN.getValue());

        System.out.println(JSONObject.toJSONString(homePageService.employeeChange(currentUserInfo,5)));
    }

    @Test
    public void queryAPPList() {
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        currentUserInfo.setUserId(1543973278535L);
        currentUserInfo.setTenantId(2L);
        currentUserInfo.setRoleType(RoleTypeEnum.COMPANY_ADMIN.getValue());

        System.out.println(JSONObject.toJSONString(homePageService.queryAPPList(currentUserInfo)));
    }

    @Test
    public void companyEmployeeChange() {
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        currentUserInfo.setUserId(1543973278535L);
        currentUserInfo.setTenantId(1073414385877123073L);
        currentUserInfo.setRoleType(RoleTypeEnum.COMPANY_ADMIN.getValue());

        System.out.println(JSONObject.toJSONString(homePageService.companyEmployeeChange(currentUserInfo)));
    }

    @Test
    public void companyUserStatistic() {
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        currentUserInfo.setUserId(1543973278535L);
        currentUserInfo.setTenantId(1073414385877123073L);
        currentUserInfo.setRoleType(RoleTypeEnum.COMPANY_ADMIN.getValue());

        System.out.println(JSONObject.toJSONString(homePageService.companyUserStatistic(currentUserInfo)));
    }
}