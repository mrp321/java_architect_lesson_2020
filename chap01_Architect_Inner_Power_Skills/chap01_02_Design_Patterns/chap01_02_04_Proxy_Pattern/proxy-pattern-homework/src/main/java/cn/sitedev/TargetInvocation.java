package cn.sitedev;

import java.lang.reflect.Method;

/**
 * 自定义invocationHandler实现
 */
public class TargetInvocation implements MyInvocationHandler {
    private Object target;

    /**
     * 返回被代理对象的实例
     *
     * @param target
     * @return
     */
    public Object getInstance(Object target) {
        this.target = target;
        Class<?> clazz = target.getClass();
        return MyProxy.newProxyInstance(new MyClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target, args);
        after();
        return result;
    }

    private void before() {
        System.out.println("TargetProxy.before...");
    }

    private void after() {
        System.out.println("TargetProxy.after");
    }
}
