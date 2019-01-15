package com.chejet.cloud.config;

import com.chejet.cloud.client.InitParam;
import com.chejet.cloud.filter.PermissionFilter;
import com.chejet.cloud.filter.SsoFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Neo.fang
 * @creatTime 2018/11/8 0008
 */
@Configuration
public class FilterConfig extends InitParam {

    @Bean
    public SsoFilter ssoFilter(){
        return new SsoFilter(authenticationRpcService, excludePathPattens);
    }

    @Bean
    public PermissionFilter permissionFilter(){
        return new PermissionFilter(authenticationRpcService, excludePathPattens);
    }
}
