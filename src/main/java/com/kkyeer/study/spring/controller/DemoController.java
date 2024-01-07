package com.kkyeer.study.spring.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/ping")
    public String pingPong(@RequestBody String bodyString) {
        for (int i = 0; i < 100; i++) {
            if (i > 15) {
                System.out.println(i + "-" + bodyString);
            }
        }
        return "pong";
    }

    @PostMapping("/ping2")
    public String pingPong2(@RequestBody TestDTO bodyObj) {
        for (int i = 0; i < 20; i++) {
            TestDTO copied = new TestDTO();
            BeanUtils.copyProperties(bodyObj, copied);
            if (i > 15) {
                System.out.println(i+"-"+copied.getF2());
            }
        }
        return "pong";
    }
    @PostMapping("/ping3")
    public String pingPong3(@RequestBody TestDTO2 bodyObj) {
        for (int i = 0; i < 20; i++) {
            TestDTO2 copied = new TestDTO2();
            copied.setF1(bodyObj.getF1());
            copied.setF2(bodyObj.getF2());
            if (i > 15) {
                System.out.println(i+"-"+copied.getF2());
            }
        }
        return "pong";
    }
}
