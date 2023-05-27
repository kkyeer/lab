package com.kkyeer.study.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: kkyeer
 * @Description: MySQL datasource
 * @Date:Created in 17:14 2023/5/27
 * @Modified By:
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("spring.datasource.mysql")
public class MySQLDataSource extends HikariDataSource {
}
