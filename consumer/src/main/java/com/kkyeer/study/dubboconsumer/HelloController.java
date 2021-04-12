package com.kkyeer.study.dubboconsumer;

import com.kkyeer.study.dubbo.user.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in  2020-4-2 11:07
 * @Modified By:
 */
@RestController
public class HelloController {
    @Reference(version = "2.0", loadbalance = "demo", check = false, timeout = 1000000)
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    @GetMapping("/hello")
    public String hello() {
        String result = userService.hello("consumer007");
        LOGGER.info(result);
        return result;
    }
}