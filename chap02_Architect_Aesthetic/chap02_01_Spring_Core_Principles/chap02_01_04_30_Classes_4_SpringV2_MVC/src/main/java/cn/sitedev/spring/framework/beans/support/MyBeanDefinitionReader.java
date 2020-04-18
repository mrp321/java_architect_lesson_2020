package cn.sitedev.spring.framework.beans.support;

import cn.sitedev.spring.framework.beans.config.MyBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MyBeanDefinitionReader {
    // 保存扫描的结果
    private List<String> registryBeanClasses = new ArrayList<>();
    // 配置文件内容
    private Properties contextConfig = new Properties();

    public MyBeanDefinitionReader(String... configLocations) {
        doLoadConfig(configLocations[0]);

        // 扫描配置文件中的配置的相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
    }

    public Properties getConfig() {
        return this.contextConfig;
    }

    // 加载BeanDefinition
    public List<MyBeanDefinition> loadBeanDefinitions() {
        List<MyBeanDefinition> result = new ArrayList<>();
        try {
            for (String className : registryBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()) {
                    continue;
                }
                // 保存类对应的className(全类名)
                // 还有beanName
                // 1. 默认是类名首字母小写
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()),
                        beanClass.getName()));
                // 2. 自定义
                // 3. 接口注入
                for (Class<?> i : beanClass.getInterfaces()) {
                    result.add(doCreateBeanDefinition(i.getName(), beanClass.getName()));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 创建BeanDefinition
    private MyBeanDefinition doCreateBeanDefinition(String beanName, String beanClassName) {
        MyBeanDefinition beanDefinition = new MyBeanDefinition(beanName, beanClassName);
        return beanDefinition;
    }

    // 扫描指定包路径下的子包以及.class文件
    private void doScanner(String scanPackage) {
        // jar, war, zip ,rar
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\."
                , "/"));
        File classPath = new File(url.getFile());

        // 当成是一个classPath文件夹
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                // 全类名 = 包名.类名
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                registryBeanClasses.add(className);
            }
        }
    }

    // 加载配置文件
    private void doLoadConfig(String contextConfigLocation) {
        // 文件 => 文件流
        InputStream inputStream =
                this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation.replaceAll("classpath:", ""));
        try {
            contextConfig.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 首字母大写转小写
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
