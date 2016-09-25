package com.caronic.jwisdom.core.exercise.spring.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by caronic on 2016/9/25.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.caronic.jwisdom.core.exercise.spring.aop")
public class AppConfig {

}
