package cn.sitedev.mybatis.v2.plugin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 包装类，对被代理对象进行包装
 */
@Data
@AllArgsConstructor
public class Invocation {
    private Object target;
    private Method method;
    private Object[] args;

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }
}
