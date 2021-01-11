package com.kkyeer.study.dubbo.dubbouserservice;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author: kkyeer
 * @Description: UserService
 * @Date:Created in  2020-2-4 22:15
 * @Modified By:
 */
@Service(version = "2.0")
@Component
public class UserServiceImpl implements UserService {
    @Value("${server.port}")
    private String port;

    @Override
    public String hello(String source) {
        return "Hi " + source + ", back from:" + port;
    }
}
