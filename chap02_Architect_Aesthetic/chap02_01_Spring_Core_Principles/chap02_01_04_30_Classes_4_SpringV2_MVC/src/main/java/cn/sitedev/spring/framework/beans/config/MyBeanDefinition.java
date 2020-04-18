package cn.sitedev.spring.framework.beans.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyBeanDefinition {
    private String factoryBeanName;
    private String beanClassName;
}
