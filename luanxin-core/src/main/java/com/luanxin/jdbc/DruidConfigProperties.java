package com.luanxin.jdbc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Component
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "spring.druid")
public class DruidConfigProperties extends DruidDataSource {

    private String url;

    private String driver;

    private Long maxPoolSize;

    private Long maxActive;

    private Long initPoolSize;

    private String username;

    private String password;

    private String driverClass;

    private String driverClassName;

    private String name;

    private Long maxWaitTime = 2000L;

}
