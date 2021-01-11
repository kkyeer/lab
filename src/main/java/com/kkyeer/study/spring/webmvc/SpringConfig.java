package com.kkyeer.study.spring.webmvc;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.kkyeer.study.spring.otherjdk.ThirdPartyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.context.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @Author: kkyeer
 * @Description: Spring配置
 * @Date:Created in 14:23 2019/8/15
 * @Modified By:
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {ScanMarker.class})
@Import(ThirdPartyConfig.class)
//@EnableConfigurationProperties(AppInfo.class)
public class SpringConfig implements WebMvcConfigurer {
    @Autowired
    private AppInfo appInfo;
    //    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.ignoreUnknownPathExtensions(false).defaultContentType(MediaType.TEXT_HTML);
//    }
//
//
//
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        converter.setSupportedMediaTypes(supportedMediaTypes);
        converters.add(converter);
    }

    @Bean("OSName")
    @Conditional(LinuxCondition.class)
    public String osNameLinux() {
        return "Linux";
    }

    @Bean("OSName")
    @Conditional(WindowsCondition.class)
    public String osNameWindows() {
        return "Windows";
    }

    @ConditionalOnBean(RedisInstance.class)
    @Bean
    public String redisName() {
        System.out.println(appInfo.getName());
        System.out.println("Got redis instance");
        return "My Redis";
    }

    @ConditionalOnWebApplication
    @Bean("rootUri")
    public String webRootPath(){
        return "http://localhost";
    }

    @ConditionalOnNotWebApplication
    @Bean("rootUri")
    public String notWebRootPath(){
        return "file://";
    }

    @Bean("cache")
    @ConditionalOnProperty("cache.enabled")
    public Map<String, Object> cache() {
        return new WeakHashMap<>();
    }

    @Bean("swarm")
    @ConditionalOnCloudPlatform(CloudPlatform.KUBERNETES)
    public String swarm(){
        return "swarm";
    }
}
