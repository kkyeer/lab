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
public class TryToOOMController {

    @GetMapping("/oom")
    public void oom() throws InterruptedException {
        final int COUNT = 33;
        byte[][] holders = new byte[COUNT][];
        for (int i = 0; i < COUNT; i++) {
            holders[i] = new byte[1024*1024];
            System.out.println(i);
        }
        Thread.sleep(1000000000);
        System.out.println(holders.length);
    }
}
