/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2016. All rights reserved.
 */

package com.huobanplus.sapservice.commons.config;

import com.huobanplus.sapservice.controller.PlatformInterceptor;
import com.huobanplus.sapservice.model.ActivityDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.Arrays;
import java.util.List;

/**
 * Created by allan on 2015/7/10.
 */
@Configuration
@EnableWebMvc
@Import(ApplicationConfig.class)
@PropertySource(value = "classpath:sapservice.properties")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private PlatformInterceptor platformInterceptor;

    @Autowired
    private Environment env;

    @Bean
    public ActivityDate test(){
        String startDate = env.getProperty("startDate");
        String endDate = env.getProperty("endDate");
        String benefitStartDate = env.getProperty("benefitStartDate");
        String benefitEndDate = env.getProperty("benefitEndDate");

        ActivityDate activityDate = new ActivityDate();
        activityDate.setStartDate(startDate);
        activityDate.setEndDate(endDate);
        activityDate.setBenefitStartDate(benefitStartDate);
        activityDate.setBenefitEndDate(benefitEndDate);
        return activityDate;
    }



    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        converters.add(converter);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authorizeInterceptor).addPathPatterns("/**/hotApi/rest/**");
//        registry.addInterceptor(userAuthorizeInterceptor).addPathPatterns("/**/hotProxy/**");
        registry.addInterceptor(platformInterceptor).addPathPatterns("/sapservice/activityManage");
//        registry.addInterceptor(sandboxInterceptor).addPathPatterns("/**/erpService/sandbox/rest/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);

        argumentResolvers.add(new AttributeArgumentResolver());
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
//        InternalResourceViewResolver resourceViewResolver = new InternalResourceViewResolver();
//        resourceViewResolver.setPrefix("/WEB-INF/views/");
//        resourceViewResolver.setSuffix(".jsp");
//        registry.viewResolver(resourceViewResolver);
        super.configureViewResolvers(registry);
        registry.viewResolver(viewResolver());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        registry.addResourceHandler("/resources/**/*").addResourceLocations("/resources/");
    }

    public ThymeleafViewResolver viewResolver() {

        ServletContextTemplateResolver rootTemplateResolver = new ServletContextTemplateResolver();
        rootTemplateResolver.setPrefix("/");
        rootTemplateResolver.setSuffix(".html");
        rootTemplateResolver.setTemplateMode("HTML5");
        rootTemplateResolver.setCharacterEncoding("UTF-8");
//        if (env.acceptsProfiles("!container")) {
//            rootTemplateResolver.setCacheable(false);
//        } else {
//            rootTemplateResolver.setCacheable(true);
//        }

        rootTemplateResolver.setCacheable(false);
        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.addDialect(new SpringSecurityDialect());
        engine.setTemplateResolver(rootTemplateResolver);

        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setOrder(1);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateEngine(engine);
        resolver.setContentType("text/html;charset=utf-8");
        return resolver;
    }

}
