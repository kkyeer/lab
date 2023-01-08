package com.kkyeer.lab.tomcat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: kkyeer
 * @Description: 被请求时，慢慢吞噬刚释放的内存
 * @Date:Created in 10:07 AM 2022/3/3
 * @Modified By:
 */
@RestController
@RequestMapping("/")
public class SlowlyConsumeMemoryController {
    @GetMapping("/consume/mb/{size}")
    public void oom(@PathVariable("size") int size) throws InterruptedException {
        try {
            byte[][] holders = new byte[size][];
            for (int i = 0; i < size; i++) {
                holders[i] = new byte[1<<20];
                System.out.println(i);
            }
            System.out.println(holders.length);
            Thread.sleep(1000000000);
            System.out.println(holders[0]);
        } catch (Throwable throwable) {
            if (throwable instanceof OutOfMemoryError) {
                System.out.println("OOOOOOOOOOOOOOOOOOOOOOM");
            }
            throw throwable;
        }

    }

    @GetMapping("/consume/kb/{size}")
    public void consumeKb(@PathVariable("size") int size) throws InterruptedException {
        try {
            byte[][] holders = new byte[size][];
            for (int i = 0; i < size; i++) {
                holders[i] = new byte[1<<10];
                System.out.println(i);
            }
            System.out.println(holders.length);
            Thread.sleep(1000000000);
            System.out.println(holders[0]);
        } catch (Throwable throwable) {
            if (throwable instanceof OutOfMemoryError) {
                System.out.println("OOOOOOOOOOOOOOOOOOOOOOM");
            }
            throw throwable;
        }

    }
}
