package com.kkyeer.study.spring.webmvc;

/**
 * @Author: kkyeer
 * @Description: 第二个服务接口，与第一个互相依赖形成依赖循环
 * @Date:Created in 14:50 2019/8/17
 * @Modified By:
 */
public interface AccountService {
    void anotherMethod();
}
