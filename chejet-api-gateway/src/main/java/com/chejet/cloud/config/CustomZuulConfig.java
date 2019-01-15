package com.chejet.cloud.config;

import com.chejet.cloud.zuul.CustomRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Neo.fang
 * @creatTime 2018/11/5 0005
 */
@Configuration
public class CustomZuulConfig {

    @Autowired
    private ZuulProperties zuulProperties;
    @Autowired
    private ServerProperties serverProperties;

    @Bean
    public CustomRouteLocator routeLocator(){
        CustomRouteLocator customRouteLocator = new CustomRouteLocator(this.serverProperties.getServlet().getServletPrefix(), this.zuulProperties);
        return customRouteLocator;
    }

}
