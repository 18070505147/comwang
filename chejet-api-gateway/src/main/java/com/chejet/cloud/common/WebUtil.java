package com.chejet.cloud.common;

import com.alibaba.fastjson.JSON;
import com.chejet.cloud.constant.SymbolicCode;
import com.chejet.cloud.response.ResultBuilder;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Neo.fang
 * @creatTime 2018/11/8 0008
 */
public class WebUtil {

    private final static Logger logger = LoggerFactory.getLogger(WebUtil.class);

    /**
     * 从request的头部获取Token信息
     */
    public static String getJsonWebToken(HttpServletRequest request) {
        String authHeader = request.getHeader(SymbolicCode.AUTHORIZATION);
        String jwt = null;
        if (authHeader == null || !authHeader.startsWith(SymbolicCode.JWT_TOKEN_PRE)) {
            logger.info("Authorization header not found");
        } else {
            jwt = authHeader.substring(7);
        }
        return jwt;
    }

    /**
     * zuul预处理response统一格式输出
     */
    public static void preResponseJson(RequestContext ctx, int code, String message) throws IOException {
        ctx.getResponse().setContentType("application/json;charset=UTF-8");
        //过滤该请求，不往下级服务去转发请求，到此结束
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(200);
        ctx.setResponseBody(JSON.toJSONString(ResultBuilder.buildCustomFail(code, message, null)));
    }

    /**
     * DispatcherServlet
     */
    public static void responseJson(HttpServletResponse response, int code, String message) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        PrintWriter writer = response.getWriter();
        writer.write(JSON.toJSONString(ResultBuilder.buildCustomFail(code, message, null)));
        writer.flush();
        writer.close();
    }
}
