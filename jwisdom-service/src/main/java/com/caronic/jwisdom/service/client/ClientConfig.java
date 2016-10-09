/**
 * 
 */
package com.caronic.jwisdom.service.client;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.caronic.jwisdom.service.config.RestServiceDetails;

/**
 * @author caronic created at 2016年10月9日
 */
@Configuration
public class ClientConfig {
    public static final String ZK_HOST = "192.168.0.129";
    
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
}
