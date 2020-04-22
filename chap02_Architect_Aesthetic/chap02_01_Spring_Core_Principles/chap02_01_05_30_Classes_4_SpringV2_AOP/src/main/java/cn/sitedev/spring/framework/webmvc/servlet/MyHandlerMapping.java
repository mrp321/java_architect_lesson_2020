package cn.sitedev.spring.framework.webmvc.servlet;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 处理器映射
 */
@Data
@AllArgsConstructor
public class MyHandlerMapping {
    // URL
    private Pattern pattern;
    // Method对应的实例
    private Object controller;
    // URL对应的Method
    private Method method;
}
