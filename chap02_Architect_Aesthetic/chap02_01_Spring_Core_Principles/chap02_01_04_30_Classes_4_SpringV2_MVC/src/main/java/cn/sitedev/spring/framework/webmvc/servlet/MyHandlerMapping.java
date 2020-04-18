package cn.sitedev.spring.framework.webmvc.servlet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyHandlerMapping {
    // URL的正则匹配
    private Pattern pattern;
    // 保存方法对应的实例
    private Object controller;
    // 保存映射的方法
    private Method method;
}
