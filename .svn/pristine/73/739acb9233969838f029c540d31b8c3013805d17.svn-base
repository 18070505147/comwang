package com.chejet.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.MultipartConfigElement;

/**
 * 文件上传配置
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/07
 */
@Configuration
@PropertySource({"classpath:application.properties"})
public class FileUploadConfig {

	@Value("${fileupload.maxFileSize}")
	String maxFileSize;

	@Value("${fileupload.maxRequestSize}")
	String maxRequestSize;

	@Bean
	public MultipartConfigElement multipartConfigElement() {

		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(maxFileSize);
		factory.setMaxRequestSize(maxRequestSize);

		return factory.createMultipartConfig();
	}
}
