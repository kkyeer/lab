package com.kkyeer.study.spring.runner;

import com.kkyeer.study.spring.service.Calculator;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 16:54 2025/7/10
 * @Modified By:
 */
@RunWith(Theories.class)
public class LTheoriesRunner {
    @DataPoints
    public static String[] expressions = new String[]{"1+1", "2+2", "3+3"};
    @DataPoints
    public static int[] results = new int[]{2, 4, 6};
    Calculator calculator = new Calculator();

    @Theory
    public void test(String expression, int result) {
        System.out.println(expression + "=" + result);
        assertEquals(result, calculator.evaluate(expression));
    }
}