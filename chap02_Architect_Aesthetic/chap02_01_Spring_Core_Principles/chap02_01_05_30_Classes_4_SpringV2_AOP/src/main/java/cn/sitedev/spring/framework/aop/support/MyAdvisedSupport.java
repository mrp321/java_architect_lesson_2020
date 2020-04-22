package cn.sitedev.spring.framework.aop.support;

import cn.sitedev.spring.framework.aop.aspect.MyAdvice;
import cn.sitedev.spring.framework.aop.config.MyAopConfig;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析AOP配置的工具类
 */
@Data
public class MyAdvisedSupport {
    private Class<?> targetClass;
    private Object target;
    private MyAopConfig config;
    private Pattern pointCutClassPattern;
    private transient Map<Method, Map<String, MyAdvice>> methodCache;

    public MyAdvisedSupport(MyAopConfig config) {
        this.config = config;
    }

    public Map<String, MyAdvice> getInterceptorsAndDynamicInterceptionAdvice(Method method,
                                                                             Class<?> targetClass) throws Exception {
        Map<String, MyAdvice> cached = methodCache.get(method);
        if (cached == null) {
            Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
            cached = methodCache.get(m);

            // 底层逻辑, 对代理方法进行一个兼容处理
            this.methodCache.put(m, cached);
        }
        return cached;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
        parse();
    }

    // 解析配置文件中的方法
    private void parse() {
        String pointCut = config.getPointCut().replaceAll("\\.", "\\\\.").replaceAll("\\\\.\\*",
                ".*").replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");

        // 保存专门匹配Class的正则
        String pointCutForClassRegex = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        pointCutClassPattern =
                Pattern.compile("class " + pointCutForClassRegex.substring(pointCutForClassRegex.lastIndexOf(" ") + 1));

        // 享元的共享池
        methodCache = new HashMap<>();
        // 保存专门匹配方法的正则
        Pattern pointCutPattern = Pattern.compile(pointCut);
        try {
            Class aspectClass = Class.forName(this.config.getAspectClass());
            Map<String, Method> aspectMethods = new HashMap<>();
            for (Method method : aspectClass.getMethods()) {
                aspectMethods.put(method.getName(), method);
            }

            for (Method method : this.targetClass.getMethods()) {
                String methodString = method.toString();
                if (methodString.contains("throws")) {
                    methodString =
                            methodString.substring(0, methodString.lastIndexOf("throws")).trim();
                }

                Matcher matcher = pointCutPattern.matcher(methodString);
                if (matcher.matches()) {
                    // 执行器链
                    Map<String, MyAdvice> advices = new HashMap<>();
                    // 把每个方法包装成MethodInterceptor
                    if (config.getAspectBefore() != null && !"".equals(config.getAspectBefore())) {
                        advices.put("before", new MyAdvice(aspectClass.newInstance(),
                                aspectMethods.get(config.getAspectBefore())));
                    }
                    if (config.getAspectAfter() != null && !"".equals(config.getAspectAfter())) {
                        advices.put("after", new MyAdvice(aspectClass.newInstance(),
                                aspectMethods.get(config.getAspectAfter())));
                    }
                    if (config.getAspectAfterThrow() != null && !"".equals(config.getAspectAfterThrow())) {
                        MyAdvice advice = new MyAdvice(aspectClass.newInstance(),
                                aspectMethods.get(config.getAspectAfterThrow()));
                        advice.setThrowName(config.getAspectAfterThrowingName());
                        advices.put("afterThrow", advice);
                    }

                    // 跟目标代理类的业务方法和Advices建立一对多关联关系, 以便在Proxy类中获得
                    methodCache.put(method, advices);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    // 根据一个目标代理类的方法, 获得其对应的通知
    public Map<String, MyAdvice> getAdvices(Method method, Object o) throws Exception {
        // 享元模式的应用
        Map<String, MyAdvice> cache = methodCache.get(method);
        if (cache == null) {
            Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
            cache = methodCache.get(method);
            this.methodCache.put(m, cache);
        }
        return cache;
    }

    // 首先IOC中的对象对应初始化时调用, 决定要不要生成代理类的逻辑
    public boolean pointCutMatch() {
        return pointCutClassPattern.matcher(this.targetClass.toString()).matches();
    }

}
