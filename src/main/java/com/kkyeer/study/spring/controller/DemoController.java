package com.kkyeer.study.spring.controller;

import com.kkyeer.study.spring.TxTestPO;
import com.kkyeer.study.spring.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @Author: kkyeer
 * @Description: DemoController
 * @Date:Created in 下午9:58 2022/11/12
 * @Modified By:
 */
@RestController
@RequestMapping("/demo")
@Slf4j
public class DemoController {
    @Autowired
    private MyService myService;


    @GetMapping("/ping")
    public String pingPong() {
        return "pong";
    }


    @GetMapping("/tx")
    @Transactional
    public String txTest() throws InterruptedException {
        TxTestPO txTestPO = new TxTestPO();
        txTestPO.setUpdateTime(LocalDateTime.now());
        myService.save(txTestPO);

        System.out.println("some other operation");
        txTestPO.setVersion(2);
        myService.updateById(txTestPO);
        return "ok";
    }
}
