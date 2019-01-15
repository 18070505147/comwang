package com.chejet.cloud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/06
 */
@Configuration
@EnableAsync(proxyTargetClass = true)
public class ExecutePoolConfiguration {
	@Value("${threadpool.corePoolSize}")
	private int corePoolSize;

	@Value("${threadpool.maxPoolSize}")
	private int maxPoolSize;

	@Value("${threadpool.queueCapacity}")
	private int queueCapacity;

	@Value("${threadpool.keepAliveSeconds}")
	private int keepAliveSeconds;

	@Primary
	@Bean
	public TaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setKeepAliveSeconds(keepAliveSeconds);
		pool.setCorePoolSize(corePoolSize);
		pool.setMaxPoolSize(maxPoolSize);
		pool.setQueueCapacity(queueCapacity);
		pool.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
		return pool;
	}
}
