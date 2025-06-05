package com.example.demo.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;
// caching configuration for the application using Caffeine cache
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES) // cache entries live for 5 minutes
                .maximumSize(100);                    // max 100 entries in cache
    }

    @Bean
    // CacheManager bean to manage caches
    // This CacheManager uses Caffeine as the underlying cache implementation
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("availableEvents","topGlobalScores","topScoresByGame","topScoresByCountryAndGame");
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }
}

