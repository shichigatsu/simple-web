package com.luanxin.jdbc;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableConfigurationProperties(DruidConfigProperties.class)
public class DruidConfig {


    @Resource
    private DruidConfigProperties druidConfigProperties;

    @Bean
    @ConditionalOnMissingBean(DruidDataSource.class)
    public DruidDataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setName(druidConfigProperties.getName());
        druidDataSource.setUrl(druidConfigProperties.getUrl());
        druidDataSource.setConnectProperties(druidConfigProperties);
        return druidDataSource;
    }
}
