package cn.sitedev.mvcframework.servlet.v3;

import cn.sitedev.mvcframework.annotation.*;
import lombok.Data;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyDispatcherServlet extends HttpServlet {
    // 保存application.properties配置文件中的内容
    private Properties contextConfig = new Properties();
    // 保存扫描到的所有的类名
    private List<String> classNames = new ArrayList<>();
    // IOC容器
    // 为了简化程序, 我们暂不考虑ConcurrentHashMap
    // 主要还是关注设计思想和原理
    private Map<String, Object> ioc = new HashMap<>();

    // 保存url和Method的对应关系
    private List<Handler> handlerMapping = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {

        // 1. 加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        // 2. 扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        // 3. 初始化扫描到的类, 并且放入到IOC容器之中
        doInstance();

        // 4. 完成自动化的依赖注入
        doAutowired();

        // 5. 初始化HandlerMapping
        doInitHandlerMapping();

        System.out.println("My Spring Framework is init...");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 6. 根据url调用method
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception, Detail: " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * 匹配URL
     *
     * @param req
     * @param resp
     * @throws Exception
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            Handler handler = getHandler(req);

            if (handler == null) {
                // 如果没有匹配上, 就返回404错误
                resp.getWriter().write("404 Not Found");
                return;
            }

            // 获取方法的参数列表
            Class<?>[] paramTypes = handler.method.getParameterTypes();

            // 保存所有需要自动赋值的参数值
            Object[] paramValues = new Object[paramTypes.length];

            // 获取请求参数map
            // 该map形如: "name"={key="name", value=["zhangsan"]}, "id"={key="id", value=["1"]}
            Map<String, String[]> params = req.getParameterMap();

            for (Map.Entry<String, String[]> param : params.entrySet()) {
                String value =
                        Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(
                                "\\s", ",");

                // 如果找到匹配的对象, 则开始填充参数值
                if (!handler.paramIndexMapping.containsKey(param.getKey())) {
                    continue;
                }
                int index = handler.paramIndexMapping.get(param.getKey());
                paramValues[index] = convert(paramTypes[index], value);
            }

            // 设置方法中的request和response对象
            int reqIndex = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
            int respIndex = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;

            handler.method.invoke(handler.controller, paramValues);

        } catch (Exception e) {
            throw e;
        }
    }

    // url传过来的参数都是String类型的, http是基于字符串协议的
    // 只需要把String转换为任意类型就好
    private Object convert(Class<?> paramType, String value) {
        if (paramType == Integer.class) {
            return Integer.valueOf(value);
        }
        // 如果还有double或者其他类型, 继续加if条件
        // 这个时候, 我们应该想到策略模式
        // 这里我们暂时不实现
        return value;
    }

    private Handler getHandler(HttpServletRequest req) {
        if (handlerMapping.isEmpty()) {
            return null;
        }

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        // 从url中截去context path
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        // 从handlerMapping中找到一个合适的handler来处理当前url
        for (Handler handler : handlerMapping) {
            try {
                Matcher matcher = handler.pattern.matcher(url);
                // 如果没有匹配上就继续下一个匹配
                if (!matcher.matches()) {
                    continue;
                }
                return handler;
            } catch (Exception e) {
                throw e;
            }
        }
        return null;
    }

    // 初始化url和Method的一对一对应关系
    private void doInitHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }

        // 遍历ioc容器中的bean
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            // 判断当前类上是否有MyController注解
            if (!clazz.isAnnotationPresent(MyController.class)) {
                continue;
            }

            // 保存写在类上面的@MyRequestMapping("/demo")
            String baseUrl = "";
            // 判断当前Controller类上是否有MyRequestMapping注解
            if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                // 获取MyRequestMapping注解
                MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            // 默认获取所有的public方法
            for (Method method : clazz.getMethods()) {
                // 判断Method方法实例上是否有MyRequestMapping注解
                // 没有的直接忽略
                if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                    continue;
                }
                // 获取MyRequestMapping注解
                MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);

                // 映射URL
                String regex = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                handlerMapping.add(new Handler(pattern, entry.getValue(), method));
                System.out.println("mapping : " + regex + ", " + method);
            }
        }
    }

    private void doAutowired() {
        if (ioc.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            // 拿到实例的所有字段
            // Declared 所有的, 特定的 字段, 包括private/protected/default
            // 正常来说, 普通的OOP编程只能拿到public的属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            // 遍历类的所有字段, 并给指定注解的字段赋值
            for (Field field : fields) {
                // 判断当前字段上是否有MyAutowired注解
                if (!field.isAnnotationPresent(MyAutowired.class)) {
                    continue;
                }
                // 获取MyAutowired注解
                MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                // 如果用户没有自定义beanName, 默认就根据类型注入
                // 这个地方省去了对类名首字母小写的情况的判断
                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    // 获得接口的类型, 作为key. 待会拿到这个key到ioc容器中去取值
                    beanName = field.getType().getName();
                }

                // 如果是public以外的修饰符, 只要加了@MyAutowired注解, 都要强制赋值
                // 反射中叫做暴力访问
                field.setAccessible(true);

                // 反射调用的方式
                // 给entry.getValue()这个对象的field字段, 赋ioc.get(beanName)这个值
                try {
                    field.set(entry.getValue(), ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue;
                }

            }

        }
    }

    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);

                // 什么样的类才需要初始化呢?
                // 加了注解的类, 才会初始化, 怎么判断?
                // 为了简化代码逻辑, 主要体会设计思想, 只举例@Controller和Service,
                //@Component... 就不一一举例了

                // 如果该类上有MyController注解
                if (clazz.isAnnotationPresent(MyController.class)) {
                    Object instance = clazz.newInstance();
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    // key:类名的首字母小写, value:类的实例
                    ioc.put(beanName, instance);
                }
                // 如果该类上有MyService注解
                else if (clazz.isAnnotationPresent(MyService.class)) {
                    // 1. 默认就根据beanName类名首字母小写
                    String beanName = toLowerFirstCase(clazz.getSimpleName());

                    // 2. 使用自定义的beanName
                    MyService service = clazz.getAnnotation(MyService.class);
                    if (!"".equals(service.value())) {
                        beanName = service.value();
                    }

                    Object instance = clazz.newInstance();
                    // key:类名的首字母小写或自定义的名称, value:类的实例
                    ioc.put(beanName, instance);

                    // 3. 根据包名.类名作为beanName
                    for (Class<?> i : clazz.getInterfaces()) {
                        if (ioc.containsKey(i.getName())) {
                            throw new Exception("The beanName is exists");
                        }
                        // 把接口的类型直接当成key了
                        // key:接口类型, value:类的实例
                        ioc.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 如果类名本身是小写字母, 确实会出问题
    // 但是我要说明的是: 这个方法是我自己用的, private类型的
    // 传值也是自己传, 存在首字母小写的情况, 也不可能出现非字母的情况
    // 为了简化程序逻辑, 就不做其他判断了
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        // 之所以采用 += , 是因为大小写字母的ASCII码值相差32
        // 而且大写字母的ASCII码要小于小写字母的
        // 在Java中, 对char做数学运算, 实际上就是对ASCII码做数学运算
        chars[0] += 32;
        return String.valueOf(chars);
    }

    // 扫描相关的类
    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\."
                , "/"));
        // scanPackage=cn.sitedev.demo, 存储的就是扫描的包路径
        // 转换为文件路径, 实际上就是将.替换成/
        // classpath下不仅有.class文件, .xml文件, .properties文件
        File classPath = new File(url.getFile());
        // 遍历该路径下的所有文件/文件夹
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                // 递归遍历
                doScanner(scanPackage + "." + file.getName());
            } else {
                // 变成 包名.类名
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                classNames.add(scanPackage + "." + file.getName().replace(".class", ""));
            }
        }
    }

    // 加载配置文件
    private void doLoadConfig(String contextConfigLocation) {
        // 直接从类路径下找到Spring主配置文件所在的路径
        // 并且将其读取出来放到Properties对象中
        // 相对于scanPackage=cn.sitedev.demo从文件中保存到了内存中
        InputStream inputStream =
                this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(inputStream);
        } catch (IOException e) {
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
    }

    /**
     * Handler记录Controller中的RequestMapping和Method的关系
     */
    @Data
    private class Handler {
        // 保存方法对应的实例
        protected Object controller;
        // 保存映射的方法
        protected Method method;
        // ${} url占位符解析
        protected Pattern pattern;
        // 参数顺序
        protected Map<String, Integer> paramIndexMapping;

        /**
         * 构造一个Handler基本的参数
         *
         * @param pattern
         * @param controller
         * @param method
         */
        public Handler(Pattern pattern, Object controller, Method method) {
            this.pattern = pattern;
            this.controller = controller;
            this.method = method;
            this.paramIndexMapping = new HashMap<>();
            putParamIndexMapping(method);
        }

        private void putParamIndexMapping(Method method) {
            // 提取方法中加了注解的参数
            Annotation[][] pa = method.getParameterAnnotations();
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
            Class<?>[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                Class<?> type = paramTypes[i];
                if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                    paramIndexMapping.put(type.getName(), i);
                }
            }

        }
    }
}
