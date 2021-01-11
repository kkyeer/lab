# SpringContext之Web上下文(1) ContextLoaderListener初始化WebApplicationContext

Servlet容器的生命周期，会调用所有servletContext中已经注册的ServletContextListener，对于注解方法配置的ServletContext来说，会调用org.springframework.web.context.ContextLoaderListener的contextInitialized方法:

```java
    @Override
    public void contextInitialized(ServletContextEvent event) {
        initWebApplicationContext(event.getServletContext());
    }
```

调用了ContextLoader的initWebApplicationContext方法:

```java
    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
        if (servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE) != null) {
            throw new IllegalStateException(
                    "Cannot initialize context because there is already a root application context present - " +
                    "check whether you have multiple ContextLoader* definitions in your web.xml!");
        }

        servletContext.log("Initializing Spring root WebApplicationContext");
        Log logger = LogFactory.getLog(ContextLoader.class);
        if (logger.isInfoEnabled()) {
            logger.info("Root WebApplicationContext: initialization started");
        }
        long startTime = System.currentTimeMillis();

        try {
            // Store context in local instance variable, to guarantee that
            // it is available on ServletContext shutdown.
            if (this.context == null) {
                this.context = createWebApplicationContext(servletContext);
            }
            if (this.context instanceof ConfigurableWebApplicationContext) {
                ConfigurableWebApplicationContext cwac = (ConfigurableWebApplicationContext) this.context;
                if (!cwac.isActive()) {
                    // The context has not yet been refreshed -> provide services such as
                    // setting the parent context, setting the application context id, etc
                    if (cwac.getParent() == null) {
                        // The context instance was injected without an explicit parent ->
                        // determine parent for root web application context, if any.
                        ApplicationContext parent = loadParentContext(servletContext);
                        cwac.setParent(parent);
                    }
                    configureAndRefreshWebApplicationContext(cwac, servletContext);
                }
            }
            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.context);

            ClassLoader ccl = Thread.currentThread().getContextClassLoader();
            if (ccl == ContextLoader.class.getClassLoader()) {
                currentContext = this.context;
            }
            else if (ccl != null) {
                currentContextPerThread.put(ccl, this.context);
            }

            if (logger.isInfoEnabled()) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                logger.info("Root WebApplicationContext initialized in " + elapsedTime + " ms");
            }

            return this.context;
        }
        catch (RuntimeException | Error ex) {
            logger.error("Context initialization failed", ex);
            servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, ex);
            throw ex;
        }
    }
```

1. 调用configureAndRefreshWebApplicationContext方法来refresh内部存储的WebApplicationContext
2. 把上一步刷新完成的WebApplicationContext放入ServletContext中

## 1.1 刷新WebApplicationContext

```java
    protected void configureAndRefreshWebApplicationContext(ConfigurableWebApplicationContext wac, ServletContext sc) {
        if (ObjectUtils.identityToString(wac).equals(wac.getId())) {
            // The application context id is still set to its original default value
            // -> assign a more useful id based on available information
            String idParam = sc.getInitParameter(CONTEXT_ID_PARAM);
            if (idParam != null) {
                wac.setId(idParam);
            }
            else {
                // Generate default id...
                wac.setId(ConfigurableWebApplicationContext.APPLICATION_CONTEXT_ID_PREFIX +
                        ObjectUtils.getDisplayString(sc.getContextPath()));
            }
        }

        wac.setServletContext(sc);
        String configLocationParam = sc.getInitParameter(CONFIG_LOCATION_PARAM);
        if (configLocationParam != null) {
            wac.setConfigLocation(configLocationParam);
        }

        // The wac environment's #initPropertySources will be called in any case when the context
        // is refreshed; do it eagerly here to ensure servlet property sources are in place for
        // use in any post-processing or initialization that occurs below prior to #refresh
        ConfigurableEnvironment env = wac.getEnvironment();
        if (env instanceof ConfigurableWebEnvironment) {
            ((ConfigurableWebEnvironment) env).initPropertySources(sc, null);
        }

        customizeContext(sc, wac);
        wac.refresh();
    }
```

1. 处理ID，默认```"org.springframework.web.context.WebApplicationContext:"```
2. 将容器的servletContext放入WebApplicationContext
3. 获取可能的"contextConfigLocation"属性
4. 初始化Environment
5. 调用Environment的方法初始化ServletPropertySource
6. 给ContextLoader的方法一个机会来在refresh之前修改WebAppContext和ServletContext
7. 调用WebApplicationContext的refresh方法

### 1.1.1 初始化Environment

初始化一个StandardServletEnvironment实例，内部的PropertySources属性包含5个PropertySource：

- "servletConfigInitParams"：new StubPropertySource
- "servletContextInitParams": new StubPropertySource,注意上面是**Config**InitParams,这个是**Context**InitParams
- "jndiProperties":new JndiPropertySource
- "systemProperties":new PropertiesPropertySource
- "systemEnvironment":new SystemEnvironmentPropertySource

其中后两个是所有ApplicationContext都会注入的PropertySource，前三个是Servlet独有的

### 1.1.2 调用Environment的方法初始化ServletPropertySource

初始化Environment完成后，会调用WebApplicationContextUtils的initServletPropertySources方法来初始化PropertySource,实际上是把Env初始化过程中的两个servlet相关的PropertySource替换：

- "servletConfigInitParams"：new ServletConfigPropertySource(name, servletConfig)
- "servletContextInitParams": new ServletContextPropertySource(name, servletContext)

### 1.1.3 ContextLoader在refresh之前自定义WebApplicationContext

获取并排序所有的ApplicationContextInitializer，然后按顺序调用：

```java
    protected void customizeContext(ServletContext sc, ConfigurableWebApplicationContext wac) {
        List<Class<ApplicationContextInitializer<ConfigurableApplicationContext>>> initializerClasses =
                determineContextInitializerClasses(sc);

        for (Class<ApplicationContextInitializer<ConfigurableApplicationContext>> initializerClass : initializerClasses) {
            Class<?> initializerContextClass =
                    GenericTypeResolver.resolveTypeArgument(initializerClass, ApplicationContextInitializer.class);
            if (initializerContextClass != null && !initializerContextClass.isInstance(wac)) {
                throw new ApplicationContextException(String.format(
                        "Could not apply context initializer [%s] since its generic parameter [%s] " +
                        "is not assignable from the type of application context used by this " +
                        "context loader: [%s]", initializerClass.getName(), initializerContextClass.getName(),
                        wac.getClass().getName()));
            }
            this.contextInitializers.add(BeanUtils.instantiateClass(initializerClass));
        }

        AnnotationAwareOrderComparator.sort(this.contextInitializers);
        for (ApplicationContextInitializer<ConfigurableApplicationContext> initializer : this.contextInitializers) {
            initializer.initialize(wac);
        }
    }
```

1. 获取所有的ApplicationContextInitializer:获取servletContext定义的"globalInitializerClasses"和"contextInitializerClasses"值
2. 实例化所有的ApplicationContextInitializer
3. 排序并按顺序调用

### 1.1.4 WebApplicationContext的refresh方法

流程与ClassPathXmlApplicationContext的refresh一致，具体参考[XmlContext的refresh方法](./XmlContext_2_refresh.md)，因为这个方法是定义在他们的共同父类AbstractApplicationContext里的，但是中间有很多细节方法是在WebApplicationContext中复写过的，具体见[WebContext的refresh方法](./WebContext_2_refresh.md)
