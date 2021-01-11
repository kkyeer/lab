package com.kkyeer.study.spring.webmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: kkyeer
 * @Description: 另外一个Service接口的实现类，依赖第一个Service接口且被其依赖
 * @Date:Created in 14:51 2019/8/17
 * @Modified By:
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UserService userService;


    @Override
    public void anotherMethod() {
        System.out.println("Another service's another method");
//        someService.serviceMethod();
    }
}
