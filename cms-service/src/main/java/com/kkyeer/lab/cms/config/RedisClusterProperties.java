package com.kkyeer.lab.cms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 下午10:06 2021/1/23
 * @Modified By:
 */
@Configuration
@PropertySource(value = "classpath:redis-conf.yml",factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "cluster")
@Data
public class RedisClusterProperties {
    private List<RedisNode> nodes;

    private Integer count;

    @Data
    public static class RedisNode {
        private String host;
    }
}
