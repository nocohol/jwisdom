package com.caronic.jwisdom.core.exercise.spring.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caronic on 2016/10/5.
 */
@Configuration
@ComponentScan(basePackages = "com.caronic.jwisdom.core.exercise.spring.data")
@PropertySource("classpath:redis.properties")
public class AppConfig {

    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private String redisPort;
    @Value("${redis.password}")
    private String redisPassword;
    @Value("${redis.maxIdle}")
    private String redisMaxIdle;
    @Value("${redis.maxWait}")
    private String redisMaxWait;
    @Value("${redis.maxActive}")
    private String redisMaxActive;
    @Value("${redis.testOnBorrow}")
    private String testOnBorrow;

    //cluster
    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;
    @Value("${spring.redis.cluster.timeout}")
    private Long timeout;
    @Value("${spring.redis.cluster.max-redirects}")
    private int maxRedirect;

//    @Autowired
//    private ClusterConfigurationProperties clusterConfigurationProperties;

    @Bean
    public JedisPoolConfig poolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.valueOf(redisMaxIdle));
        config.setMaxWaitMillis(Long.valueOf(redisMaxWait));
        config.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
        return new JedisPoolConfig();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisHost);
        factory.setPort(Integer.valueOf(redisPort));
        factory.setPassword(redisPassword);
        factory.setPoolConfig(poolConfig());
        return factory;
    }

    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        Map<String, Object> source = new HashMap<>();
        source.put("spring.redis.cluster.nodes", clusterNodes);
        source.put("spring.redis.cluster.timeout", timeout);
        source.put("spring.redis.cluster.max-redirects", maxRedirect);
        return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
    }

    @Bean
    public RedisConnectionFactory connectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfiguration());
        factory.setPoolConfig(poolConfig());
        return factory;
    }

//    @Bean
//    public RedisClusterConnection redisClusterConnection() {
//        return (RedisClusterConnection)connectionFactory().getConnection();
//    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory());
        return template;
    }

}
