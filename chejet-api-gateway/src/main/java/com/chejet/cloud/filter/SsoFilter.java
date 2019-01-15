package com.chejet.cloud.filter;

import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.common.WebUtil;
import com.chejet.cloud.config.JWTHelper;
import com.chejet.cloud.rpc.AuthenticationRpcService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Neo.fang
 * @creatTime 2018/11/8 0008
 */
public class SsoFilter extends ZuulFilter {

    private final PathMatcher pathMatcher = new AntPathMatcher();

    private AuthenticationRpcService authenticationRpcService;

    private String[] excludePathPattens;

    public SsoFilter(AuthenticationRpcService authenticationRpcService, String[] excludePathPattens) {
        this.authenticationRpcService = authenticationRpcService;
        this.excludePathPattens = excludePathPattens;
        Assert.notNull(this.authenticationRpcService, "AuthenticationRpcService init failure");
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        //判断是否在过滤url之外, false则不执行
        boolean isExcludedPage = true;
        if (excludePathPattens != null && excludePathPattens.length != 0){
            for (String url:excludePathPattens) {
                String servletPath = request.getServletPath();
                if (pathMatcher.match(url, servletPath)) {
                    isExcludedPage = false;
                    break;
                }
            }
        }

        return isExcludedPage;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        // 验证sso token有效性
        try {
            isAccessAllowed(request,ctx);
        } catch (IOException e) {
            // todo
            ctx.setResponseBody("Authentication Security failed");
        }

        return null;
    }


    public void isAccessAllowed(HttpServletRequest request, RequestContext ctx) throws IOException {
        String token = WebUtil.getJsonWebToken(request);

        // 无jwt返回到登录页面，否则验证jwt的有效性
        if (token == null) {
            WebUtil.preResponseJson(ctx, ErrorCodeEnum.USER_TOKEN_ERROR.getCode(), ErrorCodeEnum.USER_TOKEN_ERROR.getMessage());
            return;
        } else if (JWTHelper.verifyJwt(token) && checkTokenValid(token)) {
            // 验证token是否有效
            return;
        }
        WebUtil.preResponseJson(ctx, ErrorCodeEnum.USER_TOKEN_ERROR.getCode(), ErrorCodeEnum.USER_TOKEN_ERROR.getMessage());
        return;
    }

    /**
     * 请求server检查token是否有效，若A系统退出登录，B系统也应该退出，所以这里需要校验token是否有效
     */
    private boolean checkTokenValid(String token){
        boolean validate = authenticationRpcService.validate(token);
        return validate;
    }
}
