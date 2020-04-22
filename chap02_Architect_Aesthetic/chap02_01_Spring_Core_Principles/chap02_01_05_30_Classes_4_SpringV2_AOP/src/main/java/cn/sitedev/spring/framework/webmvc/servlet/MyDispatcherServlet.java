package cn.sitedev.spring.framework.webmvc.servlet;

import cn.sitedev.spring.framework.annotation.MyController;
import cn.sitedev.spring.framework.annotation.MyRequestMapping;
import cn.sitedev.spring.framework.context.MyApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 委派模式
 * 职责:负责任务调度, 请求分发
 */
public class MyDispatcherServlet extends HttpServlet {
    private MyApplicationContext applicationContext;

    private List<MyHandlerMapping> handlerMappings = new ArrayList<>();

    private Map<MyHandlerMapping, MyHandlerAdapter> handlerAdapters = new HashMap<>();

    private List<MyViewResolver> viewResolvers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //6、委派,根据URL去找到一个对应的Method并通过response返回
        try {
            this.doDispatch(req, resp);
        } catch (Exception e) {
            try {
                Map<String, Object> model500 = new HashMap<>();
                model500.put("detail", e.getMessage());
                model500.put("stackTrace", Arrays.toString(e.getStackTrace()));
                MyModelAndView mav500 = new MyModelAndView("500", model500);
                processDispatchResult(req, resp, mav500);
            } catch (Exception e2) {
                e2.printStackTrace();
                resp.getWriter().write("500 Exception, Detail : " + Arrays.toString(e.getStackTrace()));
            }
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 完成对HandlerMapping的封装
        // 完成对方法返回值的封装ModelAndView

        // 1. 通过URL获得一个HandlerMapping
        MyHandlerMapping handler = getHandler(req);
        if (handler == null) {
            processDispatchResult(req, resp, new MyModelAndView("404"));
            return;
        }

        // 2. 根据一个HandlerMapping获得一个HandlerAdapter
        MyHandlerAdapter ha = getHandlerAdapter(handler);

        // 3. 解析某一个方法的形参和返回值之后, 统一封装为ModelAndView对象
        MyModelAndView mav = ha.handle(req, resp, handler);

        // 就把ModelAndView变成一个ViewResolver
        processDispatchResult(req, resp, mav);
    }

    private MyHandlerAdapter getHandlerAdapter(MyHandlerMapping handler) {
        if (this.handlerAdapters.isEmpty()) {
            return null;
        }
        return this.handlerAdapters.get(handler);
    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp,
                                       MyModelAndView modelAndView) throws Exception {
        if (modelAndView == null) {
            return;
        }
        if (this.viewResolvers.isEmpty()) {
            return;
        }

        for (MyViewResolver viewResolver : this.viewResolvers) {
            MyView view = viewResolver.resolveViewName(modelAndView.getViewName());
            // 直接往浏览器输出
            view.render(modelAndView.getModel(), req, resp);
            return;
        }
    }

    private MyHandlerMapping getHandler(HttpServletRequest req) {
        if (this.handlerMappings.isEmpty()) {
            return null;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");

        for (MyHandlerMapping handlerMapping : this.handlerMappings) {
            Matcher matcher = handlerMapping.getPattern().matcher(url);
            if (!matcher.matches()) {
                continue;
            }
            return handlerMapping;
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化Spring核心IoC容器
        applicationContext = new MyApplicationContext(config.getInitParameter(
                "contextConfigLocation"));

        // 完成IoC, DI 和 MVC 部分对接

        // 初始化九大组件
        initStrategies(applicationContext);

        System.out.println("My Spring framework is init.");
    }

    private void initStrategies(MyApplicationContext applicationContext) {
//        // 初始化多文件上传的组件
//        initMultipartResolver(applicationContext);
//        // 初始化本地语言环境
//        initLocaleResolver(applicationContext);
//        // 初始化模板处理器
//        initThemeResolver(applicationContext);
        // 初始化处理器映射器
        initHandlerMappings(applicationContext);
        // 初始化处理器适配器
        initHandlerAdapters(applicationContext);
//        // 初始化异常拦截器
//        initHandlerExceptionResolvers(applicationContext);
//        // 初始化视图预处理器
//        initRequestToViewNameTranslator(applicationContext);
        // 初始化视图解析器
        initViewResolvers(applicationContext);
//        // FlashMap管理器
//        initFlashMapManager(applicationContext);
    }

    private void initViewResolvers(MyApplicationContext applicationContext) {
        String templateRoot = applicationContext.getConfig().getProperty("templateRoot");
        String templateRootPath =
                this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);
        for (File file : templateRootDir.listFiles()) {
            this.viewResolvers.add(new MyViewResolver(templateRoot));
        }
    }

    private void initHandlerAdapters(MyApplicationContext applicationContext) {
        for (MyHandlerMapping handlerMapping : handlerMappings) {
            this.handlerAdapters.put(handlerMapping, new MyHandlerAdapter());
        }
    }

    private void initHandlerMappings(MyApplicationContext applicationContext) {
        if (applicationContext.getBeanDefinitionCount() == 0) {
            return;
        }

        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Object instance = applicationContext.getBean(beanName);
            Class<?> clazz = instance.getClass();

            if (!clazz.isAnnotationPresent(MyController.class)) {
                continue;
            }

            // 相当于提取class上的配置的url
            String baseUrl = "";
            if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            // 只获取public的方法
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                    continue;
                }
                // 提取每个方法上配置的url
                MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);

                // //demo//query =>  /demo/query
                String regex = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("\\*",
                        ".*").replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);

                handlerMappings.add(new MyHandlerMapping(pattern, instance, method));

                System.out.println("Mapped: " + regex + ", " + method);
            }
        }
    }
}
