package com.caronic.jwisdom.core.exercise.spring.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by caronic on 2016/10/7.
 */
@Configuration
@ComponentScan(basePackages = "com.caronic.jwisdom.core.exercise.spring.cache")
@EnableCaching
public class AppConfig {

    @Bean("simpleCacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        Collection<Cache> caches = new HashSet<>();
        Cache user = new ConcurrentMapCache("user");
        Cache users = new ConcurrentMapCache("users");
//        ConcurrentMapCacheFactoryBean defaultCache = new ConcurrentMapCacheFactoryBean();
//        ConcurrentMapCacheFactoryBean users = new ConcurrentMapCacheFactoryBean();
//        users.setName("users");
        caches.add(user);
        caches.add(users);
        cacheManager.setCaches(caches);
        return cacheManager;
    }

}
