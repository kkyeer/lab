package com.kkyeer.study.spring.controller;

import com.kkyeer.study.spring.dal.ck.SomeMapper;
import com.kkyeer.study.spring.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kkyeer
 * @Description: DemoController
 * @Date:Created in 下午9:58 2022/11/12
 * @Modified By:
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private SomeMapper someMapper;

    @GetMapping("/ping")
    public String pingPong(){
        someMapper.insert(new User());
        return "pong";
    }
}
