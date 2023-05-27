package com.kkyeer.study.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: kkyeer
 * @Description: ClickHouseDataSource
 * @Date:Created in 17:06 2023/5/27
 * @Modified By:
 */

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("spring.datasource.ck")
public class ClickHouseDataSource extends HikariDataSource {

}
