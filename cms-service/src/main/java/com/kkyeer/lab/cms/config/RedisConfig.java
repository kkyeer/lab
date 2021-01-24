package com.kkyeer.lab.cms.config;

import io.lettuce.core.RedisClient;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: kkyeer
 * @Description: Redis Configuration
 * @Date:Created in 下午9:32 2021/1/23
 * @Modified By:
 */
@Configuration
public class RedisConfig {
    @Bean("userRedisList")
    public List<RedissonClient> redisClients(@Autowired RedisClusterProperties redisClusterProperties) {
        List<RedissonClient> result = new ArrayList<>();
        for (RedisClusterProperties.RedisNode nodeConfig : redisClusterProperties.getNodes()) {
            Config config = new Config();
            config.useSingleServer().setAddress(nodeConfig.getHost());
            result.add(Redisson.create(config));
        }
        return result;
    }
}
