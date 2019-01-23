package com.troila.tjsmesp.spider.config;
/**
 * 数据源二
 * @author xuanguojing
 *
 */

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactorySecondary",
        transactionManagerRef="transactionManagerSecondary",
        basePackages= { "com.troila.tjsmesp.spider.repository.informix" }) //设置Repository所在位置
public class SecondaryConfig {
	//自动注入配置好的数据源
	@Autowired
    @Qualifier("secondaryDataSource")
    private DataSource secondaryDataSource;
	
	@Value("${spring.jpa.hibernate.secondary-dialect}")
    private String secondaryDialect;
	
    @Autowired
    private JpaProperties jpaProperties;
	
    @Bean(name = "entityManagerSecondary")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }
    
    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondaryDataSource)
                .properties(getVendorProperties(secondaryDataSource))
                .packages("com.troila.tjsmesp.spider.model.secondary") //设置实体类所在位置
                .persistenceUnit("secondaryPersistenceUnit")
                .build();
    }

    private Map getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
    	Map<String,String> map = new HashMap<>();
        map.put("hibernate.dialect",secondaryDialect);
        jpaProperties.setProperties(map);
    	return jpaProperties.getHibernateProperties(new HibernateSettings());
    }
    
    @Bean(name = "transactionManagerSecondary")
    PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }
}
