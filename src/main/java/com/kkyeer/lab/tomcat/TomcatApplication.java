package com.kkyeer.lab.tomcat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TomcatApplication {

    /**
     * 启动时增加参数  -Xms55m -Xmx55m
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(TomcatApplication.class, args);
    }

}
