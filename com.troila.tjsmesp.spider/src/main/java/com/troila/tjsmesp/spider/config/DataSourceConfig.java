package com.troila.tjsmesp.spider.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {
	
	/**
	 * mysql数据源配置
	 * @Primary：自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常
	 * @return
	 */
	@Bean(name="primaryDataSource")
	@Qualifier("primaryDataSource")
	@Primary  //设置主数据源
	@ConfigurationProperties(prefix="spring.datasource.primary")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	/**
	 * informix数据源配置
	 *  
	 * @return
	 */
	@Bean(name="secondaryDataSource")
	@Qualifier("secondaryDataSource")	
	@ConfigurationProperties(prefix="spring.datasource.secondary")
	public DataSource secondaryDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	
}
