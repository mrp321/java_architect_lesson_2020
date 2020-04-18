package cn.sitedev.spring.framework.beans;

import lombok.Data;

@Data
public class MyBeanWrapper {
    private Object wrapperInstance;
    private Class<?> wrappedClass;

    public MyBeanWrapper(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
        this.wrappedClass = wrapperInstance.getClass();
    }
}
