package com.caronic.jwisdom.data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by caronic on 2016/5/7.
 */
@Profile("dev")
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryPrimary",
        transactionManagerRef = "transactionManager",
        basePackages = "com.caronic.jwisdom.data.repository")
@EnableTransactionManagement
@PropertySource(value = "classpath:dev/dataApplication.properties")
public class DevDatabaseConfig {
    @Autowired
    private JpaProperties jpaProperties;

    @Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.primaryDataSource")
    public DataSource primaryDataSource() {
        System.out.println("------- primary data source init -----------");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.secondaryDataSource")
    public DataSource secondaryDataSource() {
        System.out.println("------- secondary data source init -----------");
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "entityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(primaryDataSource())
                .packages("com.caronic.jwisdom.data.domain")
                .persistenceUnit("persistenceUnit")
                .build();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
    }
}
