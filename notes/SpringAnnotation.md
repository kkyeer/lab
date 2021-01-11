# Spring Boot核心注解

Spring Boot极大地简化了项目的配置，最大化地实现了“约定大于配置”的原则。然而基于Spring Boot虽然极大地方便了开发，但是也很容易让人“云里雾里”，特别是各种注解很容易让人“知其然而不知其所以然。所以，要想用好Spring Boot就必须对其提供的各类功能注解有一个全面而清晰地认识和理解。

## 1. SpringCore相关6个注解

### 1.1. @Configuration

从Spring3.0，@Configuration用于定义配置类，可替换xml配置文件，被注解的类内部包含有一个或多个被@Bean注解的方法，这些方法将会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，并用于构建bean定义，初始化Spring容器。

```java
@Configuration
public class TaskAutoConfiguration {

    @Bean
    public BizElectrfenceControllerJob bizElectrfenceControllerJob() {
        return new BizElectrfenceControllerJob();
    }
}
```

### 1.2. @ComponentScan

扫描对应的包、类的同级包下面有注解@Component的类，注意，很多常用继承了此注解，因此也会被自动扫描到，如@Service，@Controller及子注解，@Repository等，实际上@SpringBootApplication注解也包含了@ComponentScan注解，所以在使用中我们也可以通过@SpringBootApplication注解的scanBasePackages属性进行配置。

```java
@ComponentScan(basePackageClasses = {ScanMarker.class})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

### 1.3. @Conditional

@Conditional是Spring4新提供的注解，通过@Conditional注解可以根据代码中设置的条件装载不同的bean，在设置条件注解之前，先要把装载的bean类去实现Condition接口，然后对该实现接口的类设置是否装载的条件。Spring Boot注解中的@ConditionalOnProperty、@ConditionalOnBean等以@Conditional*开头的注解，都是通过集成了@Conditional来实现相应功能的。

#### 1.3.1 @Conditional和Condition接口的使用

1. 定义多个Condition对象

```java
public class WindowsCondition  implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return "Windows".equals(System.getProperty("os.name"));
    }
}
```

```java
public class LinuxCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !"Windows".equals(System.getProperty("os.name"));
    }
}
```

手动使用@Conditional来定义Bean

```java
    @Bean("OSName")
    @Conditional(LinuxCondition.class)
    public String osNameLinux() {
        return "Linux";
    }

    @Bean("OSName")
    @Conditional(WindowsCondition.class)
    public String osNameWindows(){
        return "Windows";
    }
```

使用：

```java
    @Resource(name="OSName")
    private String osName;
```

osName会根据运行时的环境自动适应

### 1.4. @Import

将没有被SpringContext管理的类(Bean,Configuration)导入至Spring容器中，比如将第三方提供的Jar包内部的Config导入

```java
    @Import(ThirdPartyConfig.class)
    public class SpringConfig implements WebMvcConfigurer {
    }
```

### 1.5. @ImportResource

和@Import类似，区别就是@ImportResource导入的是配置文件。

@ImportResource("classpath:spring-redis.xml")      //导入xml配置

### 1.6. @Component

@Component是元注解，Spring的(默认)ComponentScan就是扫描对应的路径下，有此注解（及继承了此注解的子注解）的类

## 2. Spring Boot相关注解

### 2.1. @SpringBootApplication

这个注解是Spring Boot最核心的注解，用在 Spring Boot的主类上，标识这是一个 Spring Boot 应用，用来开启 Spring Boot 的各项能力。实际上这个注解是@Configuration,@EnableAutoConfiguration,@ComponentScan三个注解的组合。由于这些注解一般都是一起使用，所以Spring Boot提供了一个统一的注解@SpringBootApplication。

```java
@SpringBootApplication(exclude = {
        DoNotInstance.class
})
public class API {
    public static void main(String[] args) {
        SpringApplication.run(API.class, args);
    }
}
```

### 2.2. @EnableAutoConfiguration

允许 Spring Boot 自动配置注解，开启这个注解之后，Spring Boot 就能根据当前类路径下的配置类（有@Configuration注解）。如：当前类路径下有 Mybatis 这个 JAR 包，MybatisAutoConfiguration 注解就能根据相关参数来配置 Mybatis 的各个 Spring Bean。@EnableAutoConfiguration实现的关键在于引入了AutoConfigurationImportSelector，其核心逻辑为selectImports方法，逻辑大致如下：从配置文件META-INF/    加载所有可能用到的自动配置类；去重，并将exclude和excludeName属性携带的类排除；过滤，将满足条件（@Conditional）的自动配置类返回。下面是MybatisPlus的Jar包配置:
![MybatisPlusSpringFactories.png](https://cdn.jsdelivr.net/gh/kkyeer/picbed/MybatisPlusSpringFactories.png)

### 2.3. @SpringBootConfiguration

这个注解就是 @Configuration 注解的变体，只是用来修饰是 Spring Boot 配置而已，或者可利于 Spring Boot 后续的扩展。

### 2.4 @ConditionalOnBean

@ConditionalOnBean(A.class)仅仅在当前上下文中存在A对象时，才会实例化一个Bean，也就是说只有当A.class 在spring的applicationContext中存在时，这个当前的bean才能够创建。

```java
    @ConditionalOnBean(RedisInstance.class)
    @Bean
    public String redisName(){
        return "My Redis";
    }
```

### 2.5. @ConditionalOnMissingBean

组合@Conditional注解，和@ConditionalOnBean注解相反，仅仅在当前上下文中不存在A对象时，才会实例化一个Bean。

### 2.6. @ConditionalOnClass

组合 @Conditional 注解，可以仅当某些类存在于**classpath**上时候才创建某个Bean。

### 2.7. @ConditionalOnMissingClass

组合@Conditional注解，和@ConditionalOnMissingClass注解相反，当classpath中没有指定的 Class才开启配置。

### 2.8. @ConditionalOnWebApplication

组合@Conditional 注解，当前项目类型是 WEB 项目才开启配置。当前项目有以下 3 种类型:ANY(任何Web项目都匹配)、SERVLET（仅但基础的Servelet项目才会匹配）、REACTIVE（只有基于响应的web应用程序才匹配）。

### 2.9. @ConditionalOnNotWebApplication

组合@Conditional注解，和@ConditionalOnWebApplication 注解相反，当前项目类型不是 WEB 项目才开启配置。

### 2.10. @ConditionalOnProperty

组合 @Conditional 注解，当指定的属性有指定的值时才开启配置。具体操作是通过其两个属性name以及havingValue来实现的，其中name用来从application.properties中读取某个属性值，如果该值为空，则返回false;如果值不为空，则将该值与havingValue指定的值进行比较，如果一样则返回true;否则返回false。如果返回值为false，则该configuration不生效；为true则生效。

```java
    @Bean("cache")
    @ConditionalOnProperty("cache.enabled")
    public Map<String, Object> cache() {
        return new WeakHashMap<>();
    }
```

### 2.11. @ConditionalOnExpression

组合 @Conditional 注解，当 SpEL 表达式为 true 时才开启配置，一般情况推荐使用@Conditional注解配置Condition实现类来使用

@Configuration
@ConditionalOnExpression("${enabled:false}")
public class BigpipeConfiguration {
    @Bean
    public OrderMessageMonitor orderMessageMonitor(ConfigContext configContext) {
        return new OrderMessageMonitor(configContext);
    }
}

### 2.12. @ConditionalOnJava

组合@Conditional 注解，当运行的 Java JVM 在指定的版本范围时才开启配置。

### 2.13. @ConditionalOnResource

组合 @Conditional 注解，当类路径下有指定的资源才开启配置。

@Bean
@ConditionalOnResource(resources="classpath:shiro.ini")
protected Realm iniClasspathRealm(){
  return new Realm();
}

### 2.14. @ConditionalOnJndi

组合 @Conditional 注解，当指定的 JNDI 存在时才开启配置。

### 2.15 @ConditionalOnCloudPlatform

组合 @Conditional 注解，当指定的云平台激活时才开启配置。

```java
    @Bean("swarm")
    @ConditionalOnCloudPlatform(CloudPlatform.KUBERNETES)
    public String swarm(){
        return "swarm";
    }
```

### 2.16. @ConditionalOnSingleCandidate

组合 @Conditional 注解，当指定的 class 在容器中只有一个 Bean，或者同时有多个但为首选时才开启配置。

### 2.17. @ConfigurationProperties

Spring Boot可使用注解的方式将自定义的properties文件映射到实体bean中，比如config.properties文件。

@Data
@ConfigurationProperties("rocketmq.consumer")
public class RocketMQConsumerProperties extends RocketMQProperties {
}

### 2.18. @EnableConfigurationProperties

当@EnableConfigurationProperties注解应用到你的@Configuration时，任何被@ConfigurationProperties注解的beans将自动被Environment属性配置。 这种风格的配置特别适合与SpringApplication的外部YAML配置进行配合使用。

### 2.19. @AutoConfigureAfter

用在自动配置类上面，表示该自动配置类需要在另外指定的自动配置类配置完之后。

如 Mybatis 的自动配置类，需要在数据源自动配置类之后。

@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MybatisAutoConfiguration {
}

### 2.20. @AutoConfigureBefore

这个和@AutoConfigureAfter注解使用相反，表示该自动配置类需要在另外指定的自动配置类配置之前。

### 2.21. @AutoConfigureOrder

指定配置加载的优先级顺序。

```java
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE) // 自动配置里面的最高优先级
@Configuration
@Import(BeanPostProcessorsRegistrar.class) // 导入内置容器的设置
public class EmbeddedServletContainerAutoConfiguration {
    @Configuration
    @ConditionalOnClass({ Servlet.class, Tomcat.class })
    @ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
    public static class EmbeddedTomcat {
        // ...
    }

    @Configuration
    @ConditionalOnClass({ Servlet.class, Server.class, Loader.class, WebAppContext.class })
    @ConditionalOnMissingBean(value = EmbeddedServletContainerFactory.class, search = SearchStrategy.CURRENT)
    public static class EmbeddedJetty {
        // ...
    }
}
```