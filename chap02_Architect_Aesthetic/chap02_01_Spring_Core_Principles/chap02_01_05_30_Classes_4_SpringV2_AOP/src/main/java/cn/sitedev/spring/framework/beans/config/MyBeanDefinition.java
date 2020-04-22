package cn.sitedev.spring.framework.beans.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyBeanDefinition {
    private String factoryBeanName;
    private String beanClassName;
}
