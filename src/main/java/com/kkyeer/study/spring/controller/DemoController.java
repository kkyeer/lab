package com.kkyeer.study.spring.controller;

import com.kkyeer.study.spring.TxTestPO;
import com.kkyeer.study.spring.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @Autowired
    @Lazy
    private DemoController demoController;


    @GetMapping("/ping")
    public String pingPong() {
        return "pong";
    }


    @GetMapping("/txSuccess")
    @Transactional
    public String txTest() throws Exception {
        TxTestPO po = this.generateNew();
        System.out.println("some other operation");
        mockUpdateSuccess(po);
        return "ok";
    }

    @GetMapping("/failByInnerInvoke")
    @Transactional
    public String failByInnerInvoke() throws Exception {
        TxTestPO po = generateNew();
        System.out.println("some other operation");
        mockUpdateFail(po);
        return "fail";
    }

    @GetMapping("/successByBeanInjection")
    @Transactional
    public String successByBeanInjection() throws Exception {
        TxTestPO po = demoController.generateNew();
        System.out.println("some other operation");
        demoController.mockUpdateFail(po);
        return "fail";
    }

    public TxTestPO generateNew(){
        TxTestPO txTestPO = new TxTestPO();
        txTestPO.setUpdateTime(LocalDateTime.now());
        myService.save(txTestPO);
        return txTestPO;
    }

    public void mockUpdateSuccess(TxTestPO txTestPO) throws Exception {
        System.out.println("some other operation");
        txTestPO.setVersion(2);
        myService.tryUpdate(txTestPO);
    }

    public void mockUpdateFail(TxTestPO txTestPO) throws Exception {
        System.out.println("some other operation");
        txTestPO.setVersion(2);
        myService.mockUpdateFail(txTestPO);
    }
}
