/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2016. All rights reserved.
 */

package com.huobanplus.sapservice.commons.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * Created by liual on 2015-10-17.
 */
@Configuration
@ComponentScan(basePackages = "com.huobanplus.erpservice.datacenter")
@EnableJpaRepositories(
        basePackages = "com.huobanplus.sapservice.repository",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@ImportResource({"classpath:config_prod.xml", "classpath:config_test.xml"})
public class DataCenterConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcTemplate getTemplate() {
        return new JdbcTemplate(dataSource);
    }
}
