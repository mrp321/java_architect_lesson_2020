package cn.sitedev;

import java.lang.reflect.Method;

/**
 * 自定义InvocationHandler
 */
public interface MyInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
