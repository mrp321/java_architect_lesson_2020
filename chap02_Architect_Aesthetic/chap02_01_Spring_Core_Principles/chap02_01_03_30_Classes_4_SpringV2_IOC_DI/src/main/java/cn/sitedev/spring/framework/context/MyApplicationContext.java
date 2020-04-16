package cn.sitedev.spring.framework.context;

import cn.sitedev.spring.framework.annotation.MyAutowired;
import cn.sitedev.spring.framework.annotation.MyController;
import cn.sitedev.spring.framework.annotation.MyService;
import cn.sitedev.spring.framework.beans.MyBeanWrapper;
import cn.sitedev.spring.framework.beans.config.MyBeanDefinition;
import cn.sitedev.spring.framework.beans.support.MyBeanDefinitionReader;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 按之前源码分析的套路, IOC, DI, MVC, AOP
 */
public class MyApplicationContext {
    // 存储注册信息的BeanDefinition
    protected final Map<String, MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private String[] configLocations;
    private MyBeanDefinitionReader reader;
    // 单例的IOC容器缓存
    private Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();
    // 通用的IOC容器
    private Map<String, MyBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    public MyApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        try {
            // 1. 定位, 定位配置文件
            this.reader = new MyBeanDefinitionReader(this.configLocations);

            // 2. 加载配置文件, 扫描相关的类, 把它们封装成BeanDefinition
            List<MyBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

            // 3. 注册, 把配置信息放到容器中(伪IOC容器)
            doRegisterBeanDefinition(beanDefinitions);

            // 4. 完成自动依赖注入
            doAutowired();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 只处理非延时加载的情况
    private void doAutowired() {
        for (Map.Entry<String, MyBeanDefinition> beanDefinitionEntry :
                this.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            try {
                getBean(beanName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void doRegisterBeanDefinition(List<MyBeanDefinition> beanDefinitions) throws Exception {
        for (MyBeanDefinition beanDefinition : beanDefinitions) {
            if (this.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new Exception("The \"" + beanDefinition.getFactoryBeanName() + "\" is " +
                        "exists");
            }
            this.beanDefinitionMap.put(beanDefinition.getBeanClassName(), beanDefinition);
            this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
        // 到这里为止, 容器初始化完毕
    }

    private Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    // 依赖注入, 从这里开始, 通过读取BeanDefinition中的信息
    // 然后, 通过反射机制创建一个实例并返回
    // Spring的做法是, 不会把最原始的对象放进去, 会用一个BeanWrapper来进行一次包装
    // 装饰器模式:
    // 1. 保留原来的OOP关系
    // 2. 我们需要对它进行扩展, 增强(为了以后AOP打基础)
    public Object getBean(String beanName) throws Exception {
        // 1. 读取配置信息
        MyBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);

        // 2. 实例化
        Object instance = instantiateBean(beanName, beanDefinition);

        // 3. 把这个对象封装到BeanWrapper中
        MyBeanWrapper beanWrapper = new MyBeanWrapper(instance);

        // 4. 把BeanWrapper存到IOC容器中
        // class A { B b; }
        // class B { A a; }
        // 先有鸡还是先有蛋的问题, 一个方法是搞不定的, 要分两次
        // 5. 拿到BeanWrapper之后, 把BeanWrapper保存到IOC容器中去
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);

        // 6. 执行依赖注入
        populateBean(beanName, new MyBeanDefinition(), beanWrapper);

        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();

    }

    private void populateBean(String beanName, MyBeanDefinition beanDefinition,
                              MyBeanWrapper beanWrapper) {
        Object instance = beanWrapper.getWrappedInstance();

        Class<?> clazz = beanWrapper.getWrappedClass();
        // 判断只有加了注解的类, 才执行依赖注入
        if (!(clazz.isAnnotationPresent(MyController.class) || clazz.isAnnotationPresent(MyService.class))) {
            return;
        }

        // 获得所有的fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(MyAutowired.class)) {
                continue;
            }

            MyAutowired autowired = field.getAnnotation(MyAutowired.class);

            String autowiredBeanName = autowired.value().trim();

            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }

            // 强制访问
            field.setAccessible(true);

            try {
                if (this.factoryBeanInstanceCache.get(autowiredBeanName) == null) {
                    continue;
                }
                field.set(instance,
                        this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    private Object instantiateBean(String beanName, MyBeanDefinition beanDefinition) {
        // 1. 拿到要实例化的对象的类名
        String className = beanDefinition.getBeanClassName();

        // 2. 反射实例化, 得到一个对象
        Object instance = null;
        try {
            // 假设默认就是单例, 细节暂且不考虑, 先把主线拉通
            if (this.factoryBeanObjectCache.containsKey(className)) {
                instance = this.factoryBeanObjectCache.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.factoryBeanObjectCache.put(className, instance);
                this.factoryBeanObjectCache.put(beanDefinition.getFactoryBeanName(), instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;

    }

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }
}
