package com.chejet.cloud.scheduler;

import com.chejet.cloud.common.AppStatusEnum;
import com.chejet.cloud.config.DTSJobConfig;
import com.chejet.cloud.po.RelTenantApp;
import com.chejet.cloud.util.DateUtils;
import org.slf4j.Logger;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobDisplayName;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.beetl.sql.core.SQLManager;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Bean.Wang
 * @Date: 2018/12/21 18:54
 */
@Component
@JobHandler(value = "APPJobHandler")
@JobDisplayName(value = "用户中心APP过期检查")
public class PushJobHandler extends IJobHandler {
    private Logger logger = LoggerFactory.getLogger(DTSJobConfig.class);
    @Autowired
    private SQLManager sqlManager;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        Date date = new Date();
        logger.info(">>>>>>>>>>> 用户中心APP过期检查 定时任务开始执行");
        try {
            List<Integer> para = new ArrayList<>(2);
            para.add(AppStatusEnum.TRIAL.getValue());
            para.add(AppStatusEnum.ACTIVATED.getValue());
            List<RelTenantApp> appList = sqlManager.lambdaQuery(RelTenantApp.class)
                    .andIn(RelTenantApp::getAppStatus, para)
                    .andLess(RelTenantApp::getExpireTime, DateUtils.getDate()+"")
                    .select();
            if (appList.size() == 0) {
                logger.info(">>>>>>>>>>> 用户中心APP过期检查 定时任务执行结束，操作正常");
                return ReturnT.SUCCESS;
            }
            appList.forEach(app -> {
                app.setAppStatus(1);
                app.setMtime(date);
                sqlManager.updateTemplateById(app);
            });
        } catch (Exception e) {
            logger.info(">>>>>>>>>>> 用户中心APP过期检查 定时任务执行结束，出现异常");
            return ReturnT.FAIL;
        }
        logger.info(">>>>>>>>>>> 用户中心APP过期检查 定时任务执行结束，操作正常");
        return ReturnT.SUCCESS;
    }
}
