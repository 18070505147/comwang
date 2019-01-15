package com.chejet.cloud.service;

import com.chejet.cloud.common.CurrentUserInfo;
import com.chejet.cloud.vo.*;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;

/**
 * @Description
 * @Date 2018/12/5 15:01
 * @Version 1.0
 */
public interface HomePageService {

    /**
     * 根据当前登录用户信息查询概览信息
     *
     * @param currentUserInfo
     * @return
     */
    BaseSummaryInfo summary(CurrentUserInfo currentUserInfo);


    /**
     * 统计图表
     *
     * @param currentUserInfo
     * @return
     */
    List<StatisticVO> statistic(CurrentUserInfo currentUserInfo);

    /**
     * 人员变动
     *
     * @param currentUserInfo
     * @param num
     * @return
     */
    EmployeeChangeVO employeeChange(CurrentUserInfo currentUserInfo, Integer num);

    /**
     * 登录日志
     *
     * @param currentUserInfo
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageQuery<LoginLogVO> tenantLog(CurrentUserInfo currentUserInfo, Integer pageNo, Integer pageSize);

    /**
     * 企业用户统计
     *
     * @param currentUserInfo
     * @return
     */
    List<StatisticVO> companyUserStatistic(CurrentUserInfo currentUserInfo);

    /**
     * 本月企业员工变动统计
     *
     * @param currentUserInfo
     * @return
     */
    Object companyEmployeeChange(CurrentUserInfo currentUserInfo);

    /**
     * 管理应用列表
     *
     * @param currentUserInfo
     * @return
     */
    List<CompanyAppVO> queryAPPList(CurrentUserInfo currentUserInfo);
}
