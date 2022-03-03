package com.kkyeer.lab.tomcat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kkyeer
 * @Description: 此Controller被请求时会导致OOM
 * @Date:Created in 10:02 AM 2022/3/3
 * @Modified By:
 */
@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        return "hya back";
    }

}
