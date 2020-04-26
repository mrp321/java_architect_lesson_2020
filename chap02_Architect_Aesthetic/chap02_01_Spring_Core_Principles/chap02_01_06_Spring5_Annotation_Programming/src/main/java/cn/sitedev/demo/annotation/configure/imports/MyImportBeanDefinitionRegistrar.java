package cn.sitedev.demo.annotation.configure.imports;

import cn.sitedev.project.entity.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 包里面如果声明了Company和Member这两个类, 才把User对象注册到IOC容器中
        boolean hasCompanyBean = registry.containsBeanDefinition("cn.sitedev.project.entity.Company");
        boolean hasMemberBean = registry.containsBeanDefinition("cn.sitedev.project.entity.Member");

        if (hasCompanyBean && hasMemberBean) {
            BeanDefinition beanDefinition = new RootBeanDefinition(User.class);
            registry.registerBeanDefinition("user", beanDefinition);
        }
    }
}
