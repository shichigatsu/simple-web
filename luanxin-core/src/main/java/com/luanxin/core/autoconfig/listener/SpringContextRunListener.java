package com.luanxin.core.autoconfig.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import com.luanxin.core.config.SpringContext;

public class SpringContextRunListener implements SpringApplicationRunListener {

    public SpringContextRunListener(SpringApplication application, String[] args) {
        System.out.println("SpringContextRunListener .................");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        ConfigurableEnvironment environment = context.getEnvironment();
        if (!environment.getPropertySources().contains("bootstrap")) {
            SpringContext.setApplicationContext(context);
        }
    }
}
