package com.kkyeer.lab.shardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 2:43 PM 2020/12/24
 * @Modified By:
 */
@SpringBootApplication
@MapperScan("com.kkyeer.lab.shardingsphere.dao")
public class Bootstrap {
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class);
    }
}
