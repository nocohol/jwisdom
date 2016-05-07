package com.caronic.jwisdom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by caronic on 2016/3/28.
 */
@SpringBootApplication
@PropertySource(value = {"dataApplication.properties"})
public class DataJpaApplicationTest {
    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplicationTest.class, args);
    }
}
