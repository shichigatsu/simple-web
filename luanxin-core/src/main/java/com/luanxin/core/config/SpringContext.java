package com.luanxin.core.config;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

public class SpringContext {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (SpringContext.applicationContext == null) {
            SpringContext.applicationContext = applicationContext;
        }
    }

    public static <T> T getBean(Class<T> requireType) {
        return getApplicationContext().getBean(requireType);
    }

    public static Object getBean(String beanName) {
        return getApplicationContext().getBean(beanName);
    }

    public static Object getBean(String beanName, Boolean ignoreNotFound) {
        if (!getApplicationContext().containsBean(beanName) && ignoreNotFound) {
            return null;
        }
        return getApplicationContext().getBean(beanName);
    }

    public static <T> T getBean(String beanName, Class<T> requireType) {
        return getApplicationContext().getBean(beanName, requireType);
    }

    public static Object getBeans(String beanName, Object... args) {
        return getApplicationContext().getBean(beanName, args);
    }

    public static <T> T getBean(Class<T> requireType, Object... args) {
        return getApplicationContext().getBean(requireType, args);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> requireType) {
        return getApplicationContext().getBeansOfType(requireType);
    }

    public Resource getResource(String location) {
        return getApplicationContext().getResource(location);
    }
}
