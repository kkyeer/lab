package com.kkyeer.study.spring.service;



import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 15:05 2025/7/9
 * @Modified By:
 */
public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.evaluate("1+2");
        assertEquals(3, result);
    }

}