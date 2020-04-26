package cn.sitedev.demo.annotation.configure.imports;

import cn.sitedev.project.entity.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {Cat.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MyConfig {

    /**
     * 给IOC容器注册Bean的方式:
     * 1. @Bean直接导入单个类
     * 2. @ComponentScan 默认扫描(@Controller, @Service, @Repository, @Component)
     * 3. @Import: 快速给容器导入Bean的方式
     * 3.1. @Import直接参数导入
     * 3.2. 实现ImportSelector, 自定义实现规则
     * 3.3. 实现ImportBeanDefinitionRegistrar
     * 4. FactoryBean:把需要注册的对象封装为FactoryBean
     * 4.1. FactoryBean:负责将Bean注册到IOC容器中
     * 4.2. BeanFactory:负责从IOC容器中获取Bean对象
     */

    @Bean
    public MyFactoryBean monkey() {
        return new MyFactoryBean();
    }
}
