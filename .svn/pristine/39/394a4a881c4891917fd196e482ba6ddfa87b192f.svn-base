package com.chejet.cloud.config;

import org.beetl.sql.ext.SnowflakeIDWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * id generator 配置
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/06
 */
@Configuration
public class IDWorkerConfiguration {
	@Bean(name = "snowflakeIDWorker")
	public SnowflakeIDWorker snowflakeIdWorker() {
		SnowflakeIDWorker idWorker = new SnowflakeIDWorker(1, 1);
		return idWorker;
	}
}
