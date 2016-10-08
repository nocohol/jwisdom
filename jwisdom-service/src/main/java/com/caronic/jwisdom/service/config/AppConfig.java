package com.caronic.jwisdom.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by caronic on 2016/10/8.
 */
@Configuration
@ComponentScan(basePackages = "com.caronic.jwisdom.service")
public class AppConfig {
    public static final String ZK_HOST = "192.168.0.129";
    public static final String SERVER_PORT = "server.port";
    public static final String SERVER_HOST = "server.host";
    public static final String CONTEXT_PATH = "rest";
}
