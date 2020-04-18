package cn.sitedev.spring.framework.context;

import cn.sitedev.spring.framework.annotation.MyAutowired;
import cn.sitedev.spring.framework.annotation.MyController;
import cn.sitedev.spring.framework.annotation.MyService;
import cn.sitedev.spring.framework.beans.MyBeanWrapper;
import cn.sitedev.spring.framework.beans.config.MyBeanDefinition;
import cn.sitedev.spring.framework.beans.support.MyBeanDefinitionReader;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 职责-完成Bean的创建和DI
 */
public class MyApplicationContext {
    private MyBeanDefinitionReader reader;

    private Map<String, MyBeanDefinition> beanDefinitionMap = new HashMap<>();

    private Map<String, MyBeanWrapper> factoryBeanInstanceCache = new HashMap<>();

    private Map<String, Object> factoryBeanObjectCache = new HashMap<>();

    public MyApplicationContext(String... configLocations) {
        // 1. 加载配置文件
        this.reader = new MyBeanDefinitionReader(configLocations);

        try {
            // 2. 解析配置文件, 封装成BeanDefinition
            List<MyBeanDefinition> beanDefinitions = this.reader.loadBeanDefinitions();

            // 3. 把BeanDefinition缓存起来
            doRegistBeanDefinition(beanDefinitions);

            doAutowired();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doAutowired() {
        // 调用getBean()
        // 这一步, 所有的bean并没有被真正实例化, 还只是配置阶段
        for (Map.Entry<String, MyBeanDefinition> beanDefinitionEntry :
                this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            getBean(beanName);
        }
    }

    private void doRegistBeanDefinition(List<MyBeanDefinition> beanDefinitions) throws Exception {
        for (MyBeanDefinition beanDefinition : beanDefinitions) {
            if (this.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new Exception("The " + beanDefinition.getFactoryBeanName() + " is exists");
            }
            this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
            this.beanDefinitionMap.put(beanDefinition.getBeanClassName(), beanDefinition);
        }
    }


    public Object getBean(Class beanClass) {
        return getBean(beanClass.getName());
    }

    // Bean的实例化, DI是从这个方法开始的
    public Object getBean(String beanName) {
        // 1. 先拿到BeanDefinition配置信息
        MyBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);

        // 2. 反射实例化newInstance()
        Object instance = instantiateBean(beanName, beanDefinition);

        // 3. 封装成BeanWrapper
        MyBeanWrapper beanWrapper = new MyBeanWrapper(instance);

        // 4. 保存到IOC容器中
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);

        // 5. 执行依赖注入
        populateBean(beanName, beanDefinition, beanWrapper);

        return beanWrapper.getWrapperInstance();
    }

    private void populateBean(String beanName, MyBeanDefinition beanDefinition,
                              MyBeanWrapper beanWrapper) {
        // 可能涉及到循环依赖
        // A {B b}
        // B {A a}
        // 用2个缓存, 循环两次
        // 1. 把第一次读取结果为空的BeanDefinition存到第一个缓存
        // 2. 等第一次循环之后, 第二次循环再检查第一次的缓存, 再进行赋值
        Object instance = beanWrapper.getWrapperInstance();

        Class<?> clazz = beanWrapper.getWrappedClass();

        if (!clazz.isAnnotationPresent(MyController.class) || clazz.isAnnotationPresent(MyService.class)) {
            return;
        }

        // 把所有的包括private/protected/default/public修饰字段都取出来
        for (Field field : clazz.getDeclaredFields()) {
            if (!field.isAnnotationPresent(MyAutowired.class)) {
                continue;
            }

            MyAutowired autowired = field.getAnnotation(MyAutowired.class);

            // 如果用户没有自定义的beanName, 就默认根据类型注入
            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                // field.getType()..getName() => 获取字段的类型
                autowiredBeanName = field.getType().getName();
            }

            // 暴力访问
            field.setAccessible(true);

            try {
                if (this.factoryBeanInstanceCache.get(autowiredBeanName) == null) {
                    continue;
                }
                // ioc.get(beanName) => 相当于通过接口的全类名拿到接口的实现的实例
                field.set(instance,
                        this.factoryBeanInstanceCache.get(autowiredBeanName).getWrapperInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }
        }
    }


    // 创建真正的实例对象
    private Object instantiateBean(String beanName, MyBeanDefinition beanDefinition) {
        String className = beanDefinition.getBeanClassName();
        Object instance = null;
        try {
            if (this.factoryBeanObjectCache.containsKey(beanName)) {
                instance = this.factoryBeanObjectCache.get(beanName);
            } else {
                Class<?> clazz = Class.forName(className);
                // 2. 默认的类名首字母小写
                instance = clazz.newInstance();
                this.factoryBeanObjectCache.put(beanName, instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }
}