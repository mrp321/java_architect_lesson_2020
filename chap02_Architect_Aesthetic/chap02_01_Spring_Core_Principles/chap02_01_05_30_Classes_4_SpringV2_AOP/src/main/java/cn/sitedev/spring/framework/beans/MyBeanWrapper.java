package cn.sitedev.spring.framework.beans;

import lombok.Getter;

@Getter
public class MyBeanWrapper {
    private Object wrapperInstance;
    private Class<?> wrappedClass;

    public MyBeanWrapper(Object instance) {
        this.wrapperInstance = instance;
        this.wrappedClass = instance.getClass();
    }
}
