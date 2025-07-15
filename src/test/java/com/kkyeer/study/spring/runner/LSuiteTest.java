package com.kkyeer.study.spring.runner;


import com.kkyeer.study.spring.service.CalculatorTest;
import com.kkyeer.study.spring.service.DemoServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 16:30 2025/7/10
 * @Modified By:
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CalculatorTest.class, DemoServiceTest.class})
public class LSuiteTest {

}
