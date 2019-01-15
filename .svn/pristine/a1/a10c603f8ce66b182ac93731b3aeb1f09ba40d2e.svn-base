package com.chejet.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.common.RoleTypeEnum;
import com.chejet.cloud.service.CompanyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CompanyServiceImplTest {

    @Autowired
    private CompanyService companyService;

    @Test
    public void appListAndDeployModule() throws Exception {
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        currentUserInfo.setUserId(1543973278535L);
        currentUserInfo.setTenantId(1073414385877123073L);
        currentUserInfo.setRoleType(RoleTypeEnum.TENANT_ADMIN.getValue());

//        System.out.println(JSON.toJSONString(companyService.appListAndDeployModule(currentUserInfo)));
    }
}