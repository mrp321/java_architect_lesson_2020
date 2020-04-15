package cn.sitedev.mvcframework.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // 类/接口/枚举
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyService {
    String value() default "";
}
