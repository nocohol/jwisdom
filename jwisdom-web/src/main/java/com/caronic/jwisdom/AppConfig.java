package com.caronic.jwisdom;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by caronic on 2016/5/8.
 */
@Configuration
@PropertySource(value = "classpath:${ENV}/application.properties")
public class AppConfig {
}
