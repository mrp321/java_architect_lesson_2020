package cn.sitedev.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * 请求url
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRequestMapping {
    String value() default "";
}
