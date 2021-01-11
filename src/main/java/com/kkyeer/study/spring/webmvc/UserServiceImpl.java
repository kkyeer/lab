package com.kkyeer.study.spring.webmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Author: kkyeer
 * @Description: 某Service接口的实现类
 * @Date:Created in 14:30 2019/8/15
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AccountService accountService;

    @PostConstruct
    public void init(){
        System.out.println("Service Post Construct");
    }

    @Override
    public void serviceMethod() {
        System.out.println("Some service's some method");
        accountService.anotherMethod();
    }
}
