/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2015. All rights reserved.
 */

package com.huobanplus.sapservice.commons.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface OpenID {
    /**
     * 如果为空字符串表示应该从URI中获取。
     * 
     * @return activityId Parameter Name
     */
    String value() default "";
}