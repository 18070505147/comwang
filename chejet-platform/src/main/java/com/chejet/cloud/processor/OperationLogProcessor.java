package com.chejet.cloud.processor;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.dao.UserOperationLogMapper;
import com.chejet.cloud.po.UserOperationLog;
import com.chejet.cloud.util.StringUtils;
import com.chejet.cloud.util.WebUtil;

/**
 * 操作日志处理器
 *
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/07
 */
@Component("operationLogProcessor")
public class OperationLogProcessor implements ILogProcessor {

    private static final Logger logger = LoggerFactory.getLogger(OperationLogProcessor.class);

    @Autowired
    private UserOperationLogMapper userOperationLogMapper;

    @Override
    public void process(LogParam logParam) {
        HttpServletRequest request = logParam.getRequest();
        UserOperationLog operationLog = new UserOperationLog();

        String type = "info";
        String requestUrl = request.getRequestURI();
        String requestMethod = request.getMethod();
        operationLog.setRequestUrl(requestUrl);
        operationLog.setRequestMethod(requestMethod);
        operationLog.setType(type);
        operationLog.setOperationType(logParam.getLog().operation_type().getValue());
        operationLog.setUserId(logParam.getUserId());
        operationLog.setRemoteIpAddress(WebUtil.getIpAddress(request));
        try {
            operationLog.setLocalIpAddress(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            operationLog.setOperationTime(logParam.getBeginTime());
            operationLog.setDescription(logParam.getLog().description());

//			operationLog.setQueryParams(StringUtils.paramToString(logParam.getJoinPoint().getArgs(),request.getParameterMap()));
            String params = JSON.toJSONString(logParam.getJoinPoint().getArgs());
            if (params.length() > 1000) {
                params = params.substring(0, 1000);
            }
            operationLog.setQueryParams(params);
            operationLog.setReturnFields(JSON.toJSONString(logParam.getObject()));
            operationLog.setMessage(logParam.getMessage());
            logger.info("执行方法信息:" + JSONObject.toJSON(operationLog));
            userOperationLogMapper.insert(operationLog);
        }
    }
}
