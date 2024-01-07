package com.kkyeer.study.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: kkyeer
 * @Description: Application服务类
 * 启动参数:  -XX:MaxMetaspaceSize=33270K -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC -Xmx1g -Xms1g
 * @Date:Created in 下午9:57 2022/11/12
 * @Modified By:
 */
@SpringBootApplication
public class SpringExperimentApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringExperimentApplication.class, args);
    }
}
