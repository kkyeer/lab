package com.kkyeer.study.spring.webmvc;

import org.springframework.context.event.ContextStartedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Author: kkyeer
 * @Description: 在BeanFactory刷新之前执行的ApplicationListener
 * @Date:Created in 9:20 10/18
 * @Modified By:
 */
@Component
@Order(0)
public class BeforeRefreshListener{
    @PostConstruct
    public void init(){
        System.out.println("Listener init 111");
    }
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    public void onApplicationEvent(ContextStartedEvent event) {
        System.out.println(event.getApplicationContext().getEnvironment());
    }
}
