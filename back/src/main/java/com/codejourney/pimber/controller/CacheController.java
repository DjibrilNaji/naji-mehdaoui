package com.codejourney.pimber.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cache")
@AllArgsConstructor
public class CacheController {
    @Autowired
    private CacheManager cacheManager;

    @GetMapping
    public Map<String, Object> getAllCaches() {
        Map<String, Object> caches = new HashMap<>();
        for (String cacheName : cacheManager.getCacheNames()) {
            Cache cache = cacheManager.getCache(cacheName);
            if (cache != null) {
                caches.put(cacheName, cache.getNativeCache().toString());
            }
        }
        return caches;
    }
}