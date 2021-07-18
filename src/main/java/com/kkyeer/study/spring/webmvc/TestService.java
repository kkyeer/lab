package com.kkyeer.study.spring.webmvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import sun.awt.geom.AreaOp;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: kkyeer
 * @Description:
 * @Date:Created in 11:35 AM 2021/5/18
 * @Modified By:
 */
@Service
public class TestService implements ApplicationContextAware {
    Logger logger = LoggerFactory.getLogger("aaa");
//    下面这种无法注入，会报错找不到Bean
//    @Resource
//    private ResultJavaBean resultJavaBean;

//    下面这种可以注入，根据变量名注入
//    @Resource
//    private ResultJavaBean someBeanWithoutSpecificName;

//    下面这种也可以正常注入，根据变量名作为BeanName注入
    @Autowired
    private ResultJavaBean someBeanWithoutSpecificName;

    @PostConstruct
    public void init(){
        logger.info("java bean-------" + someBeanWithoutSpecificName.getMsg());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(String.join(",", applicationContext.getBeanNamesForType(ResultJavaBean.class)));
    }
}
