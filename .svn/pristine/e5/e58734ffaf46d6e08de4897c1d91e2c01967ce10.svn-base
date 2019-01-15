package com.chejet.cloud.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * 连接池配置信息
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/04
 */
@Configuration
@RefreshScope
public class DruidConfiguration {

	@Value("${druid.datasource.url}")
	private String url; // = "jdbc:mysql://192.168.1.94:3306/ucenter?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=Hongkong&autoReconnect=true";

	@Value("${druid.datasource.username}")
	private String username; // = "carlt";

	@Value("${druid.datasource.password}")
	private String password; // = "carlt";

	@Value("${druid.datasource.maxActive}")
	private int maxActive = 100;

	@Value("${druid.datasource.initialSize}")
	private int initialSize = 5;

	@Value("${druid.datasource.maxActive}")
	private long maxWaitMillis = 5000;

	@Value("${druid.datasource.minIdle}")
	private int minIdle = 5;

	@Value("${druid.datasource.timeBetweenEvictionRunsMillis}")
	private long timeBetweenEvictionRunsMillis = 90000;

	@Value("${druid.datasource.minEvictableIdleTimeMillis}")
	private long minEvictableIdleTimeMillis = 300000;

	@Value("${druid.datasource.validationQuery}")
	private String validationQuery = "SELECT 1";

	@Value("${druid.datasource.testWhileIdle}")
	private boolean testWhileIdle = true;

	@Value("${druid.datasource.testOnBorrow}")
	private boolean testOnBorrow = true;

	@Value("${druid.datasource.testOnReturn}")
	private boolean testOnReturn = false;

	@Bean(name = "dataSource")
	@RefreshScope
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
