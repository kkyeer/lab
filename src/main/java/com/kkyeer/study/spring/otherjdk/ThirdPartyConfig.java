package com.kkyeer.study.spring.otherjdk;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: kkyeer
 * @Description: 第三方提供的JDK对应的Config
 * @Date:Created in  2020-2-6 18:05
 * @Modified By:
 */
@Configuration
public class ThirdPartyConfig {
    @Bean
    public String thirdPartyName(){
        return "ThirdParty";
    }
}
