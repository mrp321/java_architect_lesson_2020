package cn.sitedev.demo.annotation.configure.componentscan;

import cn.sitedev.project.controller.MyController2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@Configuration
// FilterType.ANNOTATION => 注解
// FilterType.ASSIGNABLE_TYPE => 类
// FilterType.CUSTOM => 自定义
@ComponentScan(value = "cn.sitedev.project", includeFilters = {@ComponentScan.Filter(type =
        FilterType.ANNOTATION, value = {Controller.class}), @ComponentScan.Filter(type =
        FilterType.ASSIGNABLE_TYPE, value = MyController2.class), @ComponentScan.Filter(type =
        FilterType.CUSTOM, value = MyTypeFilter.class)}, useDefaultFilters = false)
public class MyConfig {
}
