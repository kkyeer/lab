# SpringContext之Web上下文(2) WebApplicationContext的refresh方法

refresh的主体流程参考[XmlContext的refresh方法](./XmlContext_2_refresh.md)：

1. 准备操作：prepareRefresh()
2. 重新加载所有的bean定义到新的beanFactory：ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory()
3. 为新的beanFactory准备beanPostProcessor,Environment等:prepareBeanFactory(beanFactory);
4. 执行子类的postProcessBeanFactory(beanFactory)方法
5. 初始化并调用所有BeanFactoryPostProcessor类型的bean
6. 注册BeanPostProcessors：registerBeanPostProcessors(beanFactory);
7. 初始化MessageSource
8. 初始化ApplicationEventMulticaster
9. 其他操作：onRefresh()
10. 注册ApplicationListener
11. 初始化还未初始化的单例bean
12. 结束refresh()
13. refresh完成后清理缓存

## 1.1 prepareRefresh阶段

流程一致，唯一的区别是WebApplicationContext调用的是```WebApplicationContextUtils.initServletPropertySources```方法来初始化PropertySource

## 1.2 obtainFreshBeanFactory阶段

在此阶段新初始化一个BeanFactory，并调用WebApplicationContext的loadBeanDefinitions方法来加载Bean定义:

```java
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        AnnotatedBeanDefinitionReader reader = getAnnotatedBeanDefinitionReader(beanFactory);
        ClassPathBeanDefinitionScanner scanner = getClassPathBeanDefinitionScanner(beanFactory);

        BeanNameGenerator beanNameGenerator = getBeanNameGenerator();
        if (beanNameGenerator != null) {
            reader.setBeanNameGenerator(beanNameGenerator);
            scanner.setBeanNameGenerator(beanNameGenerator);
            beanFactory.registerSingleton(AnnotationConfigUtils.CONFIGURATION_BEAN_NAME_GENERATOR, beanNameGenerator);
        }

        ScopeMetadataResolver scopeMetadataResolver = getScopeMetadataResolver();
        if (scopeMetadataResolver != null) {
            reader.setScopeMetadataResolver(scopeMetadataResolver);
            scanner.setScopeMetadataResolver(scopeMetadataResolver);
        }

        if (!this.annotatedClasses.isEmpty()) {
            reader.register(ClassUtils.toClassArray(this.annotatedClasses));
        }

        if (!this.basePackages.isEmpty()) {
            scanner.scan(StringUtils.toStringArray(this.basePackages));
        }

        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            for (String configLocation : configLocations) {
                try {
                    Class<?> clazz = ClassUtils.forName(configLocation, getClassLoader());
                    reader.register(clazz);
                }
                catch (ClassNotFoundException ex) {
                }
            }
        }
    }
```

1. 获取注解Bean定义Reader
2. 获取ClassPath类型的Bean定义Scanner
3. 获取BeanNameGenerator
4. 获取ScopeMetadataResolver
5. 调用第1步初始化的AnnotatedBeanDefinitionReader读取配置类中@Bean等注解的Bean
6. 调用第2步初始化的ClassPathBeanDefinitionScanner在包中扫描注解过的Bean(@Component注解，其他的注解@Service,,@Repository,@Controller实际上在定义时就有@Component注解)
7. 读取ConfigLocation的xml类型的Bean定义

### 1.2.1 初始化注解Bean定义Reader：AnnotatedBeanDefinitionReader

```java
    public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry registry, Environment environment) {
        Assert.notNull(registry, "BeanDefinitionRegistry must not be null");
        Assert.notNull(environment, "Environment must not be null");
        this.registry = registry;
        this.conditionEvaluator = new ConditionEvaluator(registry, environment, null);
        AnnotationConfigUtils.registerAnnotationConfigProcessors(this.registry);
    }
```

重点在最后一步，注册了一堆解析注解时框架需要用到的BeanDefinition：

- "org.springframework.context.annotation.internalConfigurationAnnotationProcessor":new RootBeanDefinition(ConfigurationClassPostProcessor.class)
- "org.springframework.context.annotation.internalAutowiredAnnotationProcessor":new RootBeanDefinition(AutowiredAnnotationBeanPostProcessor.class)
- "org.springframework.context.annotation.internalCommonAnnotationProcessor":new RootBeanDefinition(CommonAnnotationBeanPostProcessor.class)
- "org.springframework.context.annotation.internalPersistenceAnnotationProcessor":提供JPA注解处理类
- "org.springframework.context.event.internalEventListenerProcessor":new RootBeanDefinition(EventListenerMethodProcessor.class)
- "org.springframework.context.event.internalEventListenerFactory":new RootBeanDefinition(DefaultEventListenerFactory.class)

### 1.2.2 初始化自动扫描的Scanner：ClassPathBeanDefinitionScanner

```java
    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters,
            Environment environment, @Nullable ResourceLoader resourceLoader) {

        Assert.notNull(registry, "BeanDefinitionRegistry must not be null");
        this.registry = registry;

        if (useDefaultFilters) {
            registerDefaultFilters();
        }
        setEnvironment(environment);
        setResourceLoader(resourceLoader);
    }
```

1. 注册默认的过滤器,此过滤器定义可扫描的注解Class对象：
    - ```Component.class```
    - ```javax.annotation.ManagedBean.class```
    - JSR-330 'javax.inject.Named'

### 1.2.3 reader读取配置类里的Bean定义:```reader.register(ClassUtils.toClassArray(this.annotatedClasses))```

 