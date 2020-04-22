package cn.sitedev.spring.framework.aop.aspect;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * 用于通知回调
 */
@Data
public class MyAdvice {
    private Object aspect;
    private Method adviceMethod;
    private String throwName;

    public MyAdvice(Object aspect, Method adviceMethod) {
        this.aspect = aspect;
        this.adviceMethod = adviceMethod;
    }
}
