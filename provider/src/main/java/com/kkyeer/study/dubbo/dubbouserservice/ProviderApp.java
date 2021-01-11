package com.kkyeer.study.dubbo.dubbouserservice;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDubbo
@RestController
public class ProviderApp {
    private static final Logger logger = LoggerFactory.getLogger(ProviderApp.class);
    @GetMapping("/hello")
    public void hello() {
        logger.info("hahhhh");
        logger.debug("ooooo");
        logger.error("laskdjflakjsdflkj");
    }


    public static void main(String[] args) {
        SpringApplication.run(ProviderApp.class, args);
    }

}
