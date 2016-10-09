package com.caronic.jwisdom.service.config;

import java.util.Arrays;

import javax.ws.rs.ext.RuntimeDelegate;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.caronic.jwisdom.service.rs.CarInfoRestService;
import com.caronic.jwisdom.service.rs.JaxRsApiApplication;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

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
    
    @Bean(destroyMethod = "shutdown", name="cxf")
    public SpringBus cxf() {
        return new SpringBus();
    }
    
    @Bean
    @DependsOn("cxf")
    public Server jaxRsServer() {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(jaxRsApiApplication(), JAXRSServerFactoryBean.class);
        factory.setServiceBeans(Arrays.<Object>asList(carInfoRestService()));
        factory.setAddress(factory.getAddress());
        factory.setProviders(Arrays.<Object>asList(jsonProvider()));
        return factory.create();
    }
    
    @Bean(initMethod="start", destroyMethod="close")
    public CuratorFramework curator() {
        return CuratorFrameworkFactory.newClient(ZK_HOST, new ExponentialBackoffRetry(1000, 3));
    }
    
    @Bean(initMethod="start", destroyMethod="close")
    public ServiceDiscovery<RestServiceDetails> discovery() {
        JsonInstanceSerializer<RestServiceDetails> serializer = 
                new JsonInstanceSerializer<>(RestServiceDetails.class);
        
        return ServiceDiscoveryBuilder.builder(RestServiceDetails.class)
                .client(curator())
                .basePath("services")
                .serializer(serializer)
                .build();
    }
    
    @Bean
    public JaxRsApiApplication jaxRsApiApplication() {
        return new JaxRsApiApplication();
    }
    
    @Bean
    public CarInfoRestService carInfoRestService() {
        return new CarInfoRestService();
    }
    
    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }
    
}
