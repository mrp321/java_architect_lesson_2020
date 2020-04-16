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
    private List<String> registryBeanClasses = new ArrayList<>();
    private Properties config = new Properties();
    // 固定配置文件中的key, 相对于xml的规范
    private static final String SCAN_PACKAGE = "scanPackage";

    public MyBeanDefinitionReader(String... locations) {
        // 通过URL定位找到其所对应的文件, 然后转换为文件流
        InputStream inputStream =
                this.getClass().getClassLoader().getResourceAsStream(locations[0].replace(
                        "classpath:", ""));
        try {
            config.load(inputStream);
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
        // 扫描指定包下的文件/文件夹
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String scanPackage) {
        // 转换为文件路径, 实际上就是把.替换为/
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\."
                , "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = (scanPackage + "." + file.getName()).replace(".class", "");
                registryBeanClasses.add(className);
            }
        }
    }

    public Properties getConfig() {
        return this.config;
    }

    // 把配置文件中扫描到的所有的配置信息转换成MyBeanDefinition对象,以便之后IOC操作方便
    public List<MyBeanDefinition> loadBeanDefinitions() {
        List<MyBeanDefinition> result = new ArrayList<>();
        try {
            for (String className : registryBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                // 如果是一个接口, 是不能被实例化的
                // 用它的实现类来实例化
                if (beanClass.isInterface()) {
                    continue;
                }

                // beanName有三种情况:
                // 1. 默认是类名首字母小写
                // 2. 自定义名字
                // 3. 接口注入
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()),
                        beanClass.getName()));

                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    // 如果是多个实现类, 只能覆盖
                    // 为什么? 因为Spring没有那么智能
                    // 这个时候, 可以自定义名字
                    result.add(doCreateBeanDefinition(i.getName(), beanClass.getName()));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    // 把每一个配置信息解析成一个BeanDefinition
    private MyBeanDefinition doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
        MyBeanDefinition beanDefinition = new MyBeanDefinition(beanClassName, factoryBeanName);
        return beanDefinition;
    }

    // 如果类名本身是小写字母, 确实会出问题
    // 但是要说明的是, 这个方法是自己用的, 是private类型的
    // 传值也是自己传, 类也遵循了驼峰命名法
    // 默认传入的值, 存在首字母小写的情况, 但不可能出现非字母的情况
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        // 之所以加, 是因为大小写字母的ASCII码相差32
        // 而且大写字母的ASCII码要小于小写字母的ASCII码
        // 在Java中,对char做数学运算, 实际上就是对ASCII码做数学运算
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
