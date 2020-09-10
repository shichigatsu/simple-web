package com.luanxin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.luanxin"})
@EnableAutoConfiguration
@SpringBootConfiguration
@EnableEurekaClient
@EnableConfigurationProperties
public class WebApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
