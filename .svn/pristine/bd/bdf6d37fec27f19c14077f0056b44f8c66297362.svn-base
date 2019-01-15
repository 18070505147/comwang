package com.chejet.cloud.client;

import com.caucho.hessian.client.HessianProxyFactory;
import com.chejet.cloud.rpc.AuthenticationRpcService;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;

/**
 * @author Neo.fang
 * @creatTime 2018/11/8 0008
 */
public class InitParam {

    // 单点登录服务端URL
    @Value("${auth.sso.server-url}")
    protected String ssoServerUrl;
    // 身份鉴权需要过滤的请求路径
    protected String[] excludePathPattens;
    // 单点登录服务端提供的RPC服务，由Spring容器注入
    protected AuthenticationRpcService authenticationRpcService;


    public InitParam() {
    }

    /**
     * 初始化加载
     */
    @PostConstruct
    public void init() {
        if (this.authenticationRpcService == null) {
            try {
                this.authenticationRpcService = (AuthenticationRpcService) new HessianProxyFactory()
                        .create(AuthenticationRpcService.class, this.ssoServerUrl + "/rpc/authenticationRpcService");
            } catch (MalformedURLException e) {
                throw new IllegalArgumentException("AuthenticationRpcService init failure");
            }
        }

        /**
         * 单点登录过滤路径, 后期从数据库配置 TODO
         */
        this.excludePathPattens = new String[]{
                "/user/login",
                "/user/add",
                "/user/validateAccount",
                "/user/resetPassword/sendMail",
                "/user/resetPassword/sendMail",
                "/user/resetPassword/activeMail/*/*",
                "/captcha/**",
                "/index.html",
                "/druid/*",
                "/auth/login/sign_in",

                "/ucenter/login/sign_in",
                "/ucenter/login/forget/password",
                "/ucenter/register/sign_up",
                "/ucenter/register/verification",
                "/ucenter/file/upload/*",
                "/ucenter/public/image/united-centre/**",
                "/ucenter/system/area",
                "/ucenter/captcha/**",
                "/ucenter/sms/**",
        };

    }

    public String getSsoServerUrl() {
        return ssoServerUrl;
    }

    public String[] getExcludePathPattens() {
        return excludePathPattens;
    }

    public AuthenticationRpcService getAuthenticationRpcService() {
        return authenticationRpcService;
    }
}
