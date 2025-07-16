package com.kkyeer.study.spring.mockito;

import com.kkyeer.study.spring.service.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 11:19 2025/7/15
 * @Modified By:
 */
@RunWith(MockitoJUnitRunner.class)
public class LMockConstructor {
    @Mock
    private DemoService demoService;

    @Test
    public void test() {
        when(demoService.getInfo()).thenReturn("mock");
        System.out.println(demoService.getInfo());
    }


//    @Test
////    Junit4 only,有Junit5的依赖会报错
//    public void test2(@Mock DemoService demoService2) {
//        when(demoService2.getInfo()).thenReturn("mock2");
//        System.out.println(demoService2.getInfo());
//    }
}
