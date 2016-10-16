package com.caronic.jwisdom.data.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by caronic on 2016/5/7.
 */
@Profile("test")
@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "testEntityManagerFactoryPrimary",
        transactionManagerRef = "testTransactionManager",
        basePackages = "com.caronic.jwisdom.data.repository")
@EnableTransactionManagement
//@PropertySource(value = {"classpath:test/dataApplication.properties"})
public class TestDatabaseConfig {

    @Bean(name = "testDataSource")
    @ConfigurationProperties(prefix = "spring.testDataSource")
    public DataSource testDataSource() {
        System.out.println("------- primary data source init -----------");
//        return DataSourceBuilder.create().build();
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:test/schema.sql").build();
    }

    @Bean(name = "testEntityManagerFactoryPrimary")
    public LocalContainerEntityManagerFactoryBean testEntityManagerFactoryPrimary(
            EntityManagerFactoryBuilder builder
    ) {
        return builder
                .dataSource(testDataSource())
                .packages("com.caronic.jwisdom.data.domain")
                .persistenceUnit("persistenceUnit")
                .build();
    }

    @Bean(name = "testTransactionManager")
    public PlatformTransactionManager testTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(testEntityManagerFactoryPrimary(builder).getObject());
    }
}
