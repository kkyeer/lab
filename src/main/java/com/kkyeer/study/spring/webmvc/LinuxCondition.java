package com.kkyeer.study.spring.webmvc;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * SomeCondition
 */
public class LinuxCondition implements org.springframework.context.annotation.Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !"Windows".equals(System.getProperty("os.name"));
    }
}
