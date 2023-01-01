package com.kkyeer.study.spring.cglib;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 16:30 2023/1/1
 * @Modified By:
 */
public class SimuDemoController {
    SimuDemoController innerDemoController;



    public void invokeEnhancedClass(){
        System.out.println("Begin");
        innerDemoController.toEnhanceMethod();
    }

    public void invokeThis(){
        System.out.println("Begin");
        toEnhanceMethod();
    }


    public void toEnhanceMethod(){
        System.out.println("toEnhanceMethod");
    }
}
