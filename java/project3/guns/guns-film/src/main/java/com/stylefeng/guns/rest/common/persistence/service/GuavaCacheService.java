package com.stylefeng.guns.rest.common.persistence.service;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author Xingrong.Jia
 * @USER forever
 * @PROJECT_NAME: guns
 * @date 2020-01-17 19:26
 */
@Component
public class GuavaCacheService {

    private Cache<String, Object> cache;

    @PostConstruct
    public void init() {
        cache = CacheBuilder.newBuilder().initialCapacity(10)
                .maximumSize(100).expireAfterWrite(30, TimeUnit.SECONDS).build();
    }


    public void set(String key, Object value) {
        cache.put(key, value);
    }


    public Object get(String key) {
        return cache.getIfPresent(key);
    }
}
