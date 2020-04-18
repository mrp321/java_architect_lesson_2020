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
    private static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    private MyApplicationContext context;

    private List<MyHandlerMapping> handlerMappings = new ArrayList<>();

    private Map<MyHandlerMapping, MyHandlerAdapter> handlerAdapters = new HashMap<>();

    private List<MyViewResolver> viewResolvers = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 6. 委派, 根据url去找到一个对应的Method并通过response返回
        try {
            this.doDispatch(req, resp);
        } catch (Exception e) {
            try {
                MyModelAndView mav500 = new MyModelAndView("500");
                Map<String, Object> model500 = new HashMap<>();
                model500.put("detail", e.getMessage());
                model500.put("stackTrace", Arrays.toString(e.getStackTrace()));
                mav500.setModel(model500);
                processDispatchResult(req, resp, mav500);
            } catch (Exception exception) {
                exception.printStackTrace();
                resp.getWriter().write("500 Exception, Details:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n"));
            }
        }
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 完成了对HandlerMapping的封装
        // 完成了对方法返回值的封装ModelAndView

        // 1. 通过从request中拿到URL, 去匹配一个HandlerMapping
        MyHandlerMapping handler = getHandler(request);

        if (handler == null) {
            processDispatchResult(request, response, new MyModelAndView("404"));
            return;
        }

        // 2. 准备调用前的参数, 根据一个HandlerMapping获得一个HandlerAdapter
        MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);

        // 3. 真正的调用方法, 返回ModelAndView存储了要传给页面的值, 和页面模板的名称
        // 解析某一个方法的形参和返回值之后, 统一封装为ModelAndView对象
        MyModelAndView modelAndView = handlerAdapter.handle(request, response, handler);

        // 就把ModelAndView变成一个ViewResolver
        // 这一步才是真正的输出
        processDispatchResult(request, response, modelAndView);
    }

    private void processDispatchResult(HttpServletRequest request, HttpServletResponse response,
                                       MyModelAndView modelAndView) throws Exception {
        // 把给我的ModelAndView变成HTML, OutputStream, json, freemarker, velocity
        if (modelAndView == null) {
            return;
        }
        // 如果ModelAndView不为null, 怎么办?
        if (this.viewResolvers.isEmpty()) {
            return;
        }

        for (MyViewResolver viewResolver : this.viewResolvers) {
            MyView view = viewResolver.resolveViewName(modelAndView.getViewName(), null);
            view.render(modelAndView.getModel(), request, response);
            return;
        }
    }

    private MyHandlerAdapter getHandlerAdapter(MyHandlerMapping handler) {
        if (this.handlerAdapters.isEmpty()) {
            return null;
        }
        MyHandlerAdapter handlerAdapter = this.handlerAdapters.get(handler);
        if (handlerAdapter.supports(handler)) {
            return handlerAdapter;
        }
        return null;
    }

    private MyHandlerMapping getHandler(HttpServletRequest request) {
        if (this.handlerMappings.isEmpty()) {
            return null;
        }
        String url = request.getRequestURI();
        String contextPath = request.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (MyHandlerMapping handlerMapping : this.handlerMappings) {
            try {
                Matcher matcher = handlerMapping.getPattern().matcher(url);
                // 如果没有匹配上继续下一个匹配
                if (!matcher.matches()) {
                    continue;
                }
                return handlerMapping;
            } catch (Exception e) {
                throw e;
            }
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1. 初始化Spring核心IoC容器ApplicationContext
        context = new MyApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));

        // 完成了IoC, DI和MVC部分对接

        // 2. 初始化Spring MVC 九大组件
        initStrategies(context);

        System.out.println("My Spring Framework is init");
    }

    // 初始化策略
    private void initStrategies(MyApplicationContext context) {
//        // 初始化多文件上传组件
//        initMultipartResolver(context);
//        // 初始化本地语言环境
//        initLocaleResolver(context);
//        // 初始化模板处理器
//        initThemeResolver(context);
        // handlerMapping, 必须实现
        initHandlerMappings(context);
        // 初始化参数适配器, 必须实现
        initHandlerAdapters(context);
//        // 初始化异常拦截器
//        initHandlerExceptionResolvers(context);
//        // 初始化视图预处理器
//        initRequestToViewNameTranslator(context);
        // 初始化视图转换器, 必须实现
        initViewResolvers(context);
//        // FlashMap管理器
//        initFlashMapManager(context);
    }

    private void initViewResolvers(MyApplicationContext context) {
        // 拿到模板的存放目录
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath =
                this.getClass().getClassLoader().getResource(templateRoot).getFile();

        File templateRootDir = new File(templateRootPath);
        String[] templates = templateRootDir.list();
        for (int i = 0; i < templates.length; i++) {
            // 这里主要为了兼容多模板, 所以模仿Spring用List保存
            // 这里代码简化了, 其实只要一个模板就可以搞定
            // 只是为了仿真, 所以还是搞了个List
            this.viewResolvers.add(new MyViewResolver(templateRoot));
        }
    }

    private void initHandlerAdapters(MyApplicationContext context) {
        // 把一个request请求变成handler, 参数都是字符串, 自动配到handler中的形参
        // 可想而知, 它要拿到HandlerMapping才能干活
        // 就意味着, 有几个HandlerMapping就有几个HandlerAdapter
        for (MyHandlerMapping handlerMapping : this.handlerMappings) {
            this.handlerAdapters.put(handlerMapping, new MyHandlerAdapter());
        }
    }

    private void initHandlerMappings(MyApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();

        try {
            for (String beanName : beanNames) {
                Object controller = context.getBean(beanName);

                Class<?> clazz = controller.getClass();

                if (!clazz.isAnnotationPresent(MyController.class)) {
                    continue;
                }

                String baseUrl = "";
                // 获取Controller的url配置
                if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                    MyRequestMapping requestMapping = clazz.getAnnotation(MyRequestMapping.class);
                    baseUrl = requestMapping.value();
                }

                // 获取Method的url配置
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    // 没有加RequestMapping注解的直接忽略
                    if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                        continue;
                    }
                    // 映射URL
                    MyRequestMapping requestMapping = method.getAnnotation(MyRequestMapping.class);

                    String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*"
                            , ".*")).replaceAll("/+", "/");

                    Pattern pattern = Pattern.compile(regex);

                    this.handlerMappings.add(new MyHandlerMapping(pattern, controller, method));
                    System.out.println("Mapped " + regex + ", " + method);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
