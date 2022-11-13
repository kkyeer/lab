package com.kkyeer.study.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: kkyeer
 * @Description: Application服务类
 * @Date:Created in 下午9:57 2022/11/12
 * @Modified By:
 */
@SpringBootApplication
@MapperScan
@EnableTransactionManagement
public class SpringExperimentApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringExperimentApplication.class, args);
    }
}
