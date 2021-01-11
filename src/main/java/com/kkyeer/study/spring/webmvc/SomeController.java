package com.kkyeer.study.spring.webmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: kkyeer
 * @Description: 某Webmvc Controller
 * @Date:Created in 14:32 2019/8/15
 * @Modified By:
 */
@RestController
@RequestMapping("/api")
public class SomeController {
    @Value("${a}")
    private String a;

    @Resource(name="OSName")
    private String osName;

    @Resource(name="thirdPartyName")
    private String thirdPartyName;

    @Resource(name="redisName")
    private String redisName;

    @Resource(name = "rootUri")
    private String rootUri;

    @Resource(name = "cache")
    private Map<String,Object> cache;

    @Autowired
    private AppInfo appInfo;

    @PostConstruct
    public void init(){
        System.out.println(redisName);
    }

    private final UserService userService;


    @Autowired
    public SomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/hello")
    public ResultJavaBean hello(@RequestParam("name") String name) {
        System.out.println(a);
        System.out.println("RedisName:" + redisName);
        System.out.println("OSName:" + osName);
        System.out.println("ThirdPartyName:" + thirdPartyName);
        System.out.println("RootUri:" + rootUri);
        System.out.println("Cache:" + cache);
        System.out.println("app info:" + appInfo);
        return new ResultJavaBean(200, "Hello " + name);
    }

    @EventListener
    public void eventListener(ContextStartedEvent event) {
        System.out.println(event.getApplicationContext().getEnvironment().getDefaultProfiles());
    }

}
