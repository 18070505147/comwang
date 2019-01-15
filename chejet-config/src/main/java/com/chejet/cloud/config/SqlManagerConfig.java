package com.chejet.cloud.config;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.BeetlSqlScannerConfigurer;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

/**
 * dao 注解和使用配置
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/04
 */
@Configuration
public class SqlManagerConfig {
	@Bean(name = "beetlSqlScannerConfigurer")
	public BeetlSqlScannerConfigurer getBeetlSqlScannerConfigurer() {
		BeetlSqlScannerConfigurer conf = new BeetlSqlScannerConfigurer();
		// mapper路径
		conf.setBasePackage("com.chejet.cloud.dao");
		// 后缀
		conf.setDaoSuffix("Mapper");
		conf.setSqlManagerFactoryBeanName("sqlManagerFactoryBean");
		return conf;
	}

	@Bean(name = "sqlManagerFactoryBean")
	@Primary
	public SqlManagerFactoryBean getSqlManagerFactoryBean(@Qualifier("dataSource") DataSource datasource) {
		SqlManagerFactoryBean factory = new SqlManagerFactoryBean();
		BeetlSqlDataSource source = new BeetlSqlDataSource();
		source.setMasterSource(datasource);
		factory.setCs(source);
		// 数据库类型
		factory.setDbStyle(new MySqlStyle());
		// 控制台或者日志系统输出执行的sql语句
		 factory.setInterceptors(new Interceptor[]{new DebugInterceptor()});
		// 开启驼峰
		factory.setNc(new UnderlinedNameConversion());
		// sql文件路径
		factory.setSqlLoader(new ClasspathLoader("/sql"));
		return factory;
	}

	@Bean(name = "txManager")
	public DataSourceTransactionManager getDataSourceTransactionManager(
			@Qualifier("dataSource") DataSource datasource) {
		DataSourceTransactionManager dsm = new DataSourceTransactionManager();
		dsm.setDataSource(datasource);
		return dsm;
	}
}
