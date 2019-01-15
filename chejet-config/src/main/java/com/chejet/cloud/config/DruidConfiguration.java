package com.chejet.cloud.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * 连接池配置信息
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/04
 */
@Configuration
@PropertySource({ "classpath:application.properties" })
public class DruidConfiguration {

	@Value("${druid.datasource.url}")
	private String url;
	@Value("${druid.datasource.username}")
	private String username;
	@Value("${druid.datasource.password}")
	private String password;
	@Value("${druid.datasource.maxActive}")
	private int maxActive;
	@Value("${druid.datasource.initSize}")
	private int initialSize;
	@Value("${druid.datasource.maxWait}")
	private long maxWaitMillis;
	@Value("${druid.datasource.minIdle}")
	private int minIdle;
	@Value("${druid.datasource.timeBetweenEvictionRunsMillis}")
	private long timeBetweenEvictionRunsMillis;
	@Value("${druid.datasource.minEvictableIdleTimeMillis}")
	private long minEvictableIdleTimeMillis;
	@Value("${druid.datasource.validationQuery}")
	private String validationQuery;
	@Value("${druid.datasource.testWhileIdle}")
	private boolean testWhileIdle;
	@Value("${druid.datasource.testOnBorrow}")
	private boolean testOnBorrow;
	@Value("${druid.datasource.testOnReturn}")
	private boolean testOnReturn;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setName("dataSource");
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		druidDataSource.setMaxActive(maxActive);
		druidDataSource.setInitialSize(initialSize);
		druidDataSource.setMaxWait(maxWaitMillis);
		druidDataSource.setMinIdle(minIdle);
		druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		druidDataSource.setValidationQuery(validationQuery);
		druidDataSource.setTestWhileIdle(testWhileIdle);
		druidDataSource.setTestOnBorrow(testOnBorrow);
		druidDataSource.setTestOnReturn(testOnReturn);
		return druidDataSource;

	}

}
