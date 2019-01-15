package com.chejet.cloud.service.impl;


import com.chejet.cloud.common.*;
import com.chejet.cloud.exception.BizException;
import com.chejet.cloud.po.*;
import com.chejet.cloud.service.HomePageService;
import com.chejet.cloud.vo.*;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 主页信息
 * @Date 2018/12/5 15:03
 * @Version 1.0
 */
@Service
public class HomePageServiceImpl implements HomePageService {
    @Autowired
    private SQLManager sqlManager;

    @Override
    public BaseSummaryInfo summary(CurrentUserInfo currentUserInfo) {
        //判断租户管理员or企业管理员
        if (currentUserInfo.getRoleType().equals(RoleTypeEnum.TENANT_ADMIN.getValue())) {
            SummaryInfoTenantAdmin summaryInfoTenantAdmin = new SummaryInfoTenantAdmin();
            Long appNum = sqlManager.lambdaQuery(RelTenantApp.class)
                    .andEq(RelTenantApp::getTenantId, currentUserInfo.getTenantId())
                    .andEq(RelTenantApp::getAppStatus, AppStatusEnum.ACTIVATED.getValue())
                    .count();
            summaryInfoTenantAdmin.setAppNum(appNum);
            Long companyNum = sqlManager.lambdaQuery(Company.class)
                    .andEq(Company::getTenantId, currentUserInfo.getTenantId())
                    .andEq(Company::getCompanyStatus, CompanyStatusEnum.ENABLE.getValue())
                    .count();
            summaryInfoTenantAdmin.setCompanyNum(companyNum);
            Map<String, Object> params = new HashMap<>();
            params.put("tenantId", currentUserInfo.getTenantId());
            Long appUserNum = sqlManager.intValue("homepage.tenant_app_user", params).longValue();
            summaryInfoTenantAdmin.setAppUserNum(appUserNum);
            return summaryInfoTenantAdmin;
        } else if (currentUserInfo.getRoleType().equals(RoleTypeEnum.COMPANY_ADMIN.getValue())) {
            SummaryInfoCompanyAdmin summaryInfoCompanyAdmin = new SummaryInfoCompanyAdmin();
            Long employeeNum = 0L;
            summaryInfoCompanyAdmin.setEmployeeNum(employeeNum);
            Long appNum = 0L;
            summaryInfoCompanyAdmin.setAppNum(appNum);
            Long companyNum = 0L;
            summaryInfoCompanyAdmin.setCompanyNum(companyNum);
            return summaryInfoCompanyAdmin;
        }
        return null;
    }

    /**
     * 按照应用 统计租户用户
     *
     * @param currentUserInfo
     * @return
     */
    @Override
    public List<StatisticVO> statistic(CurrentUserInfo currentUserInfo) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("tenantId", currentUserInfo.getTenantId());
            List<StatisticPO> queryList = sqlManager.select("homepage.tenant_user_statistic_by_app", StatisticPO.class, params);

            Map<Long, StatisticVO> convertMap = new HashMap<>();
            statisticUserOnlineAndOffline(queryList, convertMap);
            List<StatisticVO> result = new ArrayList(convertMap.values());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.DB_SELECT_EXCEPTION);
        }
    }

    /**
     * 按照应用id或者企业id重组离在线人数统计
     *
     * @param queryList
     * @param convertMap
     */
    private void statisticUserOnlineAndOffline(List<StatisticPO> queryList, Map<Long, StatisticVO> convertMap) {
        if (!CollectionUtils.isEmpty(queryList)) {
            for (StatisticPO spo : queryList) {
                if (convertMap.containsKey(spo.getId())) {
                    StatisticVO svo = convertMap.get(spo.getId());
                    svo.setName(spo.getName());
                    if (spo.getStatus().equals(1)) {
                        svo.setOnlineNum(spo.getNum());
                    } else {
                        svo.setOfflineNum(spo.getNum());
                    }
                    svo.setTotal(svo.getOnlineNum() + svo.getOfflineNum());
                } else {
                    StatisticVO svo = new StatisticVO();
                    svo.setName(spo.getName());
                    if (spo.getStatus().equals(1)) {
                        svo.setOnlineNum(spo.getNum());
                    } else {
                        svo.setOfflineNum(spo.getNum());
                    }
                    svo.setTotal(svo.getOnlineNum() + svo.getOfflineNum());
                    convertMap.put(spo.getId(), svo);
                }
            }
        }
    }

    @Override
    public EmployeeChangeVO employeeChange(CurrentUserInfo currentUserInfo, Integer num) {

        if (currentUserInfo.getRoleType().equals(RoleTypeEnum.TENANT_ADMIN.getValue())) {
            EmployeeChangeVO employeeChangeVO = new EmployeeChangeVO();
            Map<String, Object> params = new HashMap<>();
            params.put("tenantId", currentUserInfo.getTenantId());
            params.put("num", num);
            employeeChangeVO.setEntry(sqlManager.select("homepage.tenant_employ_entry", EmployeeChangeVO.EmplyChange.class, params));
            employeeChangeVO.setLeave(sqlManager.select("homepage.tenant_employ_leave", EmployeeChangeVO.EmplyChange.class, params));

            return employeeChangeVO;
        } else {
            EmployeeChangeVO employeeChangeVO = new EmployeeChangeVO();
            Map<String, Object> params = new HashMap<>();
            params.put("tenantId", currentUserInfo.getTenantId());
            params.put("userId", currentUserInfo.getUserId());
            params.put("num", num);
            employeeChangeVO.setEntry(sqlManager.select("homepage.company_employ_entry", EmployeeChangeVO.EmplyChange.class, params));
            employeeChangeVO.setLeave(sqlManager.select("homepage.company_employ_leave", EmployeeChangeVO.EmplyChange.class, params));

            return employeeChangeVO;
        }
    }

    /**
     * 分页请求登录日志
     *
     * @param currentUserInfo
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public PageQuery<LoginLogVO> tenantLog(CurrentUserInfo currentUserInfo, Integer pageNo, Integer pageSize) {
        Long tenantId = currentUserInfo.getTenantId();
        Map<String, Object> params = new HashMap<>();
        params.put("tenantId", tenantId);
        PageQuery<LoginLogVO> result = new PageQuery<>();
        PageQuery<UserLoginLog> query = new PageQuery<>();
        query.setParas(params);
        query.setPageNumber(pageNo);
        query.setPageSize(pageSize);
        query = sqlManager.pageQuery("homepage.login_log", UserLoginLog.class, query);
        List<LoginLogVO> logList = new ArrayList<>();
        for (UserLoginLog log : query.getList()) {
            User user = (User) log.get("user");
            App app = (App) log.get("app");

            LoginLogVO loginLogVO = new LoginLogVO();
            loginLogVO.setAppName(app.getName());
            loginLogVO.setLoginDevice(log.getLoginDevice());
            loginLogVO.setLoginTime(log.getLoginTime());
            loginLogVO.setUserName(user.getNickname());

            logList.add(loginLogVO);
        }
        result.setList(logList);
        result.setPageSize(query.getPageSize());
        result.setPageNumber(query.getPageNumber());
        result.setTotalRow(query.getTotalRow());
        return result;
    }

    /**
     * 按照企业统计在线情况
     *
     * @param currentUserInfo
     * @return
     */
    @Override
    public List<StatisticVO> companyUserStatistic(CurrentUserInfo currentUserInfo) {
        // TODO 统计逻辑可能不对，当用户在多家企业任职时候，统计可能有误
        try {

            Map<String, Object> params = new HashMap<>();
            params.put("tenantId", currentUserInfo.getTenantId());
            params.put("userId", currentUserInfo.getUserId());
            List<StatisticPO> queryList = sqlManager.select("homepage.company_admin_user_statistic", StatisticPO.class, params);
            Map<Long, StatisticVO> convertMap = new HashMap<>();
            statisticUserOnlineAndOffline(queryList, convertMap);
            List<StatisticVO> result = new ArrayList(convertMap.values());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.DB_SELECT_EXCEPTION);
        }
    }

    /**
     * 按照企业统计离职入职情况
     *
     * @param currentUserInfo
     * @return
     */
    @Override
    public List<CompanyEmployeeChangeVO> companyEmployeeChange(CurrentUserInfo currentUserInfo) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("tenantId", currentUserInfo.getTenantId());
            params.put("userId", currentUserInfo.getUserId());

            List<EmpChange> queryList = sqlManager.select("homepage.company_admin_employee_change", EmpChange.class, params);
            // 重组queryList查询结果

            Map<Long, CompanyEmployeeChangeVO> convertMap = new HashMap<>();
            if (!CollectionUtils.isEmpty(queryList)) {
                for (EmpChange empChange : queryList) {
                    if (convertMap.containsKey(empChange.getId())) {
                        CompanyEmployeeChangeVO ceco = convertMap.get(empChange.getId());
                        if (empChange.getStatus().equals(1)) {
                            ceco.setEntryNum(empChange.getNum());
                        } else {
                            ceco.setLeaveNum(empChange.getNum());
                        }
                    } else {
                        CompanyEmployeeChangeVO ceco = new CompanyEmployeeChangeVO();
                        ceco.setName(empChange.getName());
                        if (empChange.getStatus().equals(1)) {
                            ceco.setEntryNum(empChange.getNum());
                        } else {
                            ceco.setLeaveNum(empChange.getNum());
                        }
                        convertMap.put(empChange.getId(), ceco);
                    }
                }
            }
            List<CompanyEmployeeChangeVO> result = new ArrayList(convertMap.values());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.DB_SELECT_EXCEPTION);
        }
    }

    @Override
    public List<CompanyAppVO> queryAPPList(CurrentUserInfo currentUserInfo) {
        List<CompanyAppVO> appVOList = new ArrayList<>();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("tenantId", currentUserInfo.getTenantId());
            params.put("userId", currentUserInfo.getUserId());
            if (currentUserInfo.getRoleType().equals(RoleTypeEnum.COMPANY_ADMIN.getValue())) {
                appVOList = sqlManager.select("homepage.company_admin_app_list", CompanyAppVO.class, params);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ErrorCodeEnum.DB_SELECT_EXCEPTION);
        }
        return appVOList;
    }

}
