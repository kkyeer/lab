package com.kkyeer.study.spring.runner;

import com.kkyeer.study.spring.service.Calculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 16:38 2025/7/10
 * @Modified By:
 */

@RunWith(Parameterized.class)
public class LParameterizedTest {
    private final String expression;

    private final int result;

    public LParameterizedTest(String expression, int result) {
        this.expression = expression;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"1+2", 3},
                {"2+3", 5},
                {"3+4", 7},
                {"4+5", 9},
                {"5+6", 11},
        };
    }

    @Test
    public void test() {
        System.out.println(expression + "=" + result);
        Calculator calculator = new Calculator();
        int result = calculator.evaluate(expression);
        assertEquals(this.result, result);
    }
}
