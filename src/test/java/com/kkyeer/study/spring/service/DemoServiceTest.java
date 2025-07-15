package com.kkyeer.study.spring.service;


import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 14:18 2025/7/9
 * @Modified By:
 */

public class DemoServiceTest {

    @Test
    public void testGetInfo() {
        DemoService demoService = new DemoService();
        String info = demoService.getInfo();
        assertEquals("Hello World!", info);
    }
}