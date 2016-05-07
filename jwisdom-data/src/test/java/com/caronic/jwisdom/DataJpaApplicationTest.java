package com.caronic.jwisdom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by caronic on 2016/3/28.
 */
@SpringBootApplication
@ActiveProfiles("test")
@PropertySource(value = {"classpath:test/dataApplication.properties"})
public class DataJpaApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplicationTest.class, args);
    }
}
