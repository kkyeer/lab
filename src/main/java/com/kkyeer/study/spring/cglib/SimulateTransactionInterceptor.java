package com.kkyeer.study.spring.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 16:30 2023/1/1
 * @Modified By:
 */
public class SimulateTransactionInterceptor implements MethodInterceptor {
    private Object originalObject;

    public Object proxyObject(Object originalObject) {
        this.originalObject = originalObject;
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        enhancer.setSuperclass(originalObject.getClass());
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Interceptor logic");
        return methodProxy.invoke(originalObject, objects);
    }
}
