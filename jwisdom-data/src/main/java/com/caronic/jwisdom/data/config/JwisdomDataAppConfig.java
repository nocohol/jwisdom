package com.caronic.jwisdom.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by caronic on 2016/5/7.
 */
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManager",
        basePackages = "com.caronic.jwisdom.data.repository")
@EnableTransactionManagement
public class JwisdomDataAppConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Bean(name = "mysqlDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.ds.mysql")
    public DataSource mysqlDataSource() {
        System.out.println("------- mysql data source init -----------");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "h2DataSource")
    @ConfigurationProperties(prefix = "spring.ds.h2")
    public DataSource h2DataSource() {
        System.out.println("------- h2 data source init -----------");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(
            EntityManagerFactoryBuilder builder
            ) {
        return builder
                .dataSource(mysqlDataSource())
                .packages("com.caronic.jwisdom.data.domain")
                .persistenceUnit("persistenceUnit")
                .build();
    }

    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }

}
