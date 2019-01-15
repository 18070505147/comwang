package com.chejet.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域处理配置
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/07
 */
//@Configuration
@PropertySource({ "classpath:application.properties" })
public class CorsConfig {
	
	@Value("${corsconfig.allowedOrigin}")
	private String allowedOrigin;
	@Value("${corsconfig.allowedHeader}")
	private String allowedHeader;
	@Value("${corsconfig.allowedMethod}")
	private String allowedMethod;
	@Value("${corsconfig.allowCredentials}")
	private boolean allowCredentials;
	@Value("${corsconfig.allowPath}")
	private String allowPath;

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin(allowedOrigin);
        corsConfiguration.addAllowedHeader(allowedHeader);
        corsConfiguration.addAllowedMethod(allowedMethod);
        corsConfiguration.setAllowCredentials(allowCredentials);
        return corsConfiguration;
    }

/*    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(allowPath, buildConfig());
        return new CorsFilter(source);
    }*/
}