package com.kkyeer.study.spring.cglib;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 16:34 2023/1/1
 * @Modified By:
 */
public class Simulator {
    public static void main(String[] args) {
        SimuDemoController controller = new SimuDemoController();
        SimuDemoController proxy = (SimuDemoController) new SimulateTransactionInterceptor().proxyObject(controller);
        controller.innerDemoController = proxy;

        controller.invokeEnhancedClass();
        controller.invokeThis();
    }
}
