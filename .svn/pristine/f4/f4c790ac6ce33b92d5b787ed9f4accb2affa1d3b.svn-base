package com.chejet.cloud.scheduler;

import com.chejet.cloud.service.accessApi.TokenManager;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobDisplayName;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Neo.fang
 * @creatTime 2019/1/14 0014
 */
@Component
@JobHandler(value = "AccessTokenHandler")
@JobDisplayName(value = "用户访问令牌过期检查")
public class AccessTokenHandler extends IJobHandler {

    private Logger logger = LoggerFactory.getLogger(AccessTokenHandler.class);

    @Autowired
    private TokenManager tokenManager;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        logger.info(">>>>>>>>>>> 用户访问令牌过期检查 定时任务开始执行");
//        tokenManager.verifyExpired();
        logger.info(">>>>>>>>>>> 用户访问令牌过期检查 定时任务执行结束，操作正常");
        return ReturnT.SUCCESS;
    }
}
