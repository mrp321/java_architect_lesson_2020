package cn.sitedev.spring.framework.aop;

import cn.sitedev.spring.framework.aop.aspect.MyAdvice;
import cn.sitedev.spring.framework.aop.support.MyAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class MyJdkDynamicAopProxy implements MyAopProxy, InvocationHandler {
    private MyAdvisedSupport advised;

    public MyJdkDynamicAopProxy(MyAdvisedSupport config) {
        this.advised = config;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.advised.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, this.advised.getTargetClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, MyAdvice> advices =
                this.advised.getInterceptorsAndDynamicInterceptionAdvice(method,
                        this.advised.getTargetClass());

        Object returnValue = null;
        try {
            invokeAdvice(advices.get("before"));
            returnValue = method.invoke(this.advised.getTarget(), args);
            invokeAdvice(advices.get("after"));
        } catch (Exception e) {
            invokeAdvice(advices.get("afterThrow"));
            throw e;
        }
        return returnValue;
    }

    private void invokeAdvice(MyAdvice advice) {
        try {
            advice.getAdviceMethod().invoke(advice.getAspect());

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
