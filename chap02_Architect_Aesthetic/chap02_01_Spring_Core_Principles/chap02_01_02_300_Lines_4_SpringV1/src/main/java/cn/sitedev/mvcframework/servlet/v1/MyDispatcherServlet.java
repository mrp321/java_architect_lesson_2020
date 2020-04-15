package cn.sitedev.mvcframework.servlet.v1;

import cn.sitedev.mvcframework.annotation.MyAutowired;
import cn.sitedev.mvcframework.annotation.MyController;
import cn.sitedev.mvcframework.annotation.MyRequestMapping;
import cn.sitedev.mvcframework.annotation.MyService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MyDispatcherServlet extends HttpServlet {
    private Map<String, Object> mapping = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception " + e.getMessage());
        }
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 获取请求url
        String url = request.getRequestURI();
        // 获取context path
        String contextPath = request.getContextPath();
        // 从请求url中截去context path
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        // 判断是否有对应的方法来处理该url对应的请求
        if (!this.mapping.containsKey(url)) {
            response.getWriter().write("404 Not Found!");
            return;
        }
        // 获取该请求url对应的方法对象
        Method method = (Method) this.mapping.get(url);
        //获取请求参数map, 该map形如: "name"={key="name",value=["zhangsan"]}, "id"={key="id",value=["1"]}
        Map<String, String[]> params = request.getParameterMap();
        // 通过反射调用处理该url对应的方法, 这里只处理了/demo/query对应的请求
        method.invoke(this.mapping.get(method.getDeclaringClass().getName()),
                new Object[]{request, response, params.get("name")[0], params.get("id")[0]});
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream inputStream = null;
        try {
            // 读取配置文件application.properties
            Properties configContext = new Properties();
            inputStream =
                    this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter(
                            "contextConfigLocation"));
            configContext.load(inputStream);
            // 获取scanPackage配置
            String scanPackage = configContext.getProperty("scanPackage");
            // 递归扫描指定包路径下的子包以及类
            doScanner(scanPackage);

            Map<String, Object> extMapping = new HashMap<>();
            // 遍历指定包路径下的类
            for (String className : mapping.keySet()) {
                if (!className.contains(".")) {
                    continue;
                }
                // 加载指定类
                Class<?> clazz = Class.forName(className);
                // 判断该类上是否有MyController注解
                if (clazz.isAnnotationPresent(MyController.class)) {
                    // key: 类的完全限定名 value:该类对应实例
                    extMapping.put(className, clazz.newInstance());
                    String baseUrl = "";
                    // 判断该类上是否有MyRequestMapping注解
                    if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                        MyRequestMapping requestMapping =
                                clazz.getAnnotation(MyRequestMapping.class);
                        // 获取MyRequestMapping注解的value值
                        baseUrl = requestMapping.value();
                    }
                    // 获取类中的所有方法对象(包含自定义的方法,以及继承自Object类的方法)
                    Method[] methods = clazz.getMethods();
                    // 遍历所有方法对象
                    for (Method method : methods) {
                        // 判断方法上是否有MyRequestMapping注解
                        if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                            continue;
                        }
                        // 获取MyRequestMapping注解的value值
                        MyRequestMapping requestMapping =
                                method.getAnnotation(MyRequestMapping.class);
                        // 拼接请求的url(如果存在多个/, 则替换成一个/)
                        String url = (baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                        // key: 请求url, value: 处理对应url的方法对象
                        extMapping.put(url, method);
                        System.out.println("Mapped : " + url + ", " + method);

                    }
                    // 判断类上是否有MyService注解
                } else if (clazz.isAnnotationPresent(MyService.class)) {
                    // 获取MyService注解的value值
                    MyService service = clazz.getAnnotation(MyService.class);
                    String beanName = service.value();
                    if ("".equals(beanName)) {
                        beanName = clazz.getName();
                    }
                    Object instance = clazz.newInstance();
                    // key:类的完全限定名, value:类的实例
                    extMapping.put(beanName, instance);
                    for (Class<?> i : clazz.getInterfaces()) {
                        // key: 类实现的接口的完全限定名, value:类的实例
                        extMapping.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }
            }

            mapping.putAll(extMapping);
            for (Object object : mapping.values()) {
                if (object == null) {
                    continue;
                }
                Class<?> clazz = object.getClass();
                // 判断类上是否有MyController注解
                if (clazz.isAnnotationPresent(MyController.class)) {
                    // 获取类所有的字段
                    Field[] fields = clazz.getDeclaredFields();
                    // 遍历类的所有字段
                    for (Field field : fields) {
                        // 判断当前字段上是否有MyAutowired注解
                        if (!field.isAnnotationPresent(MyAutowired.class)) {
                            continue;
                        }
                        // 获取MyAutowired注解的value值
                        MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                        String beanName = autowired.value();
                        if ("".equals(beanName)) {
                            beanName = field.getType().getName();
                        }
                        // 使该字段可被访问
                        field.setAccessible(true);
                        // 给该字段设置值
                        field.set(mapping.get(clazz.getName()), mapping.get(beanName));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("My MVC framework is init !");

    }

    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\."
                , "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                // 递归扫描
                doScanner(scanPackage + "." + file.getName());
            } else {
                // 处理.class文件
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                // 存入mapping => key:类的完全限定名, value:null
                String clazzName = scanPackage + "." + file.getName().replace(".class", "");
                mapping.put(clazzName, null);
            }
        }
    }
}
