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