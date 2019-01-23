package com.troila.tjsmesp.spider.config;

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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 数据源一
 * @author xuanguojing
 *
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryPrimary",  //配置连接工厂 entityManagerFactory
        transactionManagerRef="transactionManagerPrimary",      //配置事物管理器  transactionManager
        basePackages= { "com.troila.tjsmesp.spider.repository.mysql" }) //设置Repository所在位置
public class PrimaryConfig {

	@Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;
	
	@Value("${spring.jpa.hibernate.primary-dialect}")
    private String primaryDialect;// 获取对应的数据库方言
	
    @Autowired
    private JpaProperties jpaProperties;
	
    @Bean(name = "entityManagerPrimary")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
    }
    
    @Bean(name = "entityManagerFactoryPrimary")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary (EntityManagerFactoryBuilder builder) {
        return builder
                //设置数据源
                .dataSource(primaryDataSource)
                //设置数据源属性
                .properties(getVendorProperties(primaryDataSource))
                //设置实体类所在位置.扫描所有带有 @Entity 注解的类
                .packages("com.troila.tjsmesp.spider.model.primary")
                // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
                // Repository就能用它来创建 EntityManager 了,然后 EntityManager 就可以针对数据库执行操作
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    private Map getVendorProperties(DataSource dataSource) {
//        return jpaProperties.getHibernateProperties(dataSource);
    	Map<String,String> map = new HashMap<>();
        map.put("hibernate.dialect",primaryDialect);// 设置对应的数据库方言
        jpaProperties.setProperties(map);
//        return jpaProperties.getHibernateProperties(dataSource);
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }
    

    /**
     * 配置事物管理器
     *
     * @param builder
     * @return
	*/
    @Bean(name = "transactionManagerPrimary")
    @Primary
    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }
}
