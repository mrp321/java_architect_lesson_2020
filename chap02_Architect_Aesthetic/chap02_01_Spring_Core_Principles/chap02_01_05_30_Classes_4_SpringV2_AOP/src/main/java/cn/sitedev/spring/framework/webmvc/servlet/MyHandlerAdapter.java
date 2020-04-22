package cn.sitedev.spring.framework.webmvc.servlet;

import cn.sitedev.spring.framework.annotation.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理器适配
 */
public class MyHandlerAdapter {
    public boolean supports(Object handler) {
        return handler instanceof MyHandlerMapping;
    }

    public MyModelAndView handle(HttpServletRequest request, HttpServletResponse response,
                                 Object handler) throws InvocationTargetException,
            IllegalAccessException {
        MyHandlerMapping handlerMapping = (MyHandlerMapping) handler;

        // 把 方法的形参列表和request的参数列表所在顺序一一对应
        Map<String, Integer> paramIndexMapping = new HashMap<>();

        // 提取方法中加了注解的参数
        // 把方法上的注解拿到, 得到的是一个二维数组
        // 因为一个参数可以有多个注解, 而一个方法又有多个参数
        Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
        for (int i = 0; i < pa.length; i++) {
            for (Annotation a : pa[i]) {
                if (a instanceof MyRequestParam) {
                    String paramName = ((MyRequestParam) a).value();
                    if (!"".equals(paramName.trim())) {
                        paramIndexMapping.put(paramName, i);
                    }
                }
            }
        }

        // 提取方法中的request和response参数
        Class<?>[] paramsTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0; i < paramsTypes.length; i++) {
            Class<?> type = paramsTypes[i];
            if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                paramIndexMapping.put(type.getName(), i);
            }
        }

        // 获得方法的形参列表
        Map<String, String[]> params = request.getParameterMap();

        // 实参列表
        Object[] paramValues = new Object[paramsTypes.length];

        for (Map.Entry<String, String[]> param : params.entrySet()) {
            String value =
                    Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll("\\s",
                            ",");
            if (!paramIndexMapping.containsKey(param.getKey())) {
                continue;
            }
            int index = paramIndexMapping.get(param.getKey());
            paramValues[index] = caseStringValue(value, paramsTypes[index]);
        }

        if (paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = request;
        }

        if (paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = response;
        }

        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(),
                paramValues);
        if (result == null || result instanceof Void) {
            return null;
        }

        boolean isModelAndView = handlerMapping.getMethod().getReturnType() == MyModelAndView.class;
        if (isModelAndView) {
            return (MyModelAndView) result;
        }
        return null;
    }

    private Object caseStringValue(String value, Class<?> paramsType) {
        if (String.class == paramsType) {
            return value;
        }
        // 如果还有double或者其他类型, 继续加if
        // 这时候, 我们应该想到策略模式
        // 在这里暂时不实现
        if (Integer.class == paramsType) {
            return Integer.valueOf(value);
        }
        if (Double.class == paramsType) {
            return Double.valueOf(value);
        }
        if (value != null) {
            return value;
        }
        return null;
    }
}
