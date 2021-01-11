package com.kkyeer.study.spring.webmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: kkyeer
 * @Description: 入口类
 * @Date:Created in 16:12 10/18
 * @Modified By:
 */
@SpringBootApplication
//@Lazy
public class Bootstrap {
    static {
//        System.out.println(System.getenv("spring.profiles.active"));
//        System.out.println(System.getProperty("spring.profiles.active"));
//        System.out.println(System.getProperties());
    }
    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class);
    }
}
