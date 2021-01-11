package com.kkyeer.study.spring.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: kkyeer
 * @Description: desc
 * @Date:Created in 18:32 2018/11/15
 * @Modified By:
 */
public class TasteClassPathXmlApplicationContext {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        Person person = applicationContext.getBean(Person.class);
        System.out.println(person);
    }
}
