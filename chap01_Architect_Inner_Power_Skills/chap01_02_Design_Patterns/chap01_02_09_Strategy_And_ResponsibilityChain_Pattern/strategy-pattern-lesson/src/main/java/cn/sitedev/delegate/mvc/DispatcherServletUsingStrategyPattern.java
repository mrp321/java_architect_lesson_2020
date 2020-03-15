package cn.sitedev.delegate.mvc;

import cn.sitedev.delegate.mvc.controller.MemberController;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServletUsingStrategyPattern extends HttpServlet {
    private List<Handler> handlerMapping = new ArrayList<>();

    @Override
    public void init() {
        Class<?> memberControllerClass = MemberController.class;
        try {
            handlerMapping.add(new Handler(memberControllerClass.newInstance(),
                    memberControllerClass.getMethod("getMemberById",
                            new Class[]{HttpServletResponse.class, String.class}),
                    "/getMemberById"));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 1. 获取用户请求的url
        // 如果按照J2EE的标准, 每个url对对应一个Servlet, url由浏览器输入
        String uri = request.getRequestURI();

        // 2. Servlet拿到url以后, 要做权衡,
        // 根据用户请求的url, 去找到这个url对应的某一个Java类的方法

        // 3. 通过拿到的url去handlerMapping(我们把它认为是策略常量)
        Handler handler = null;
        for (Handler h : handlerMapping) {
            if (uri.equals(h.getUrl())) {
                handler = h;
                break;
            }
        }
        if (handler == null) {
            response.getWriter().write("<h1>404 NOT FOUND......</h1>");
            return;
        }
        // 4. 将具体的任务分发给Method(通过反射去调用其对应的方法)
//        Object object = null;
        try {
            handler.getMethod().invoke(handler.getController(), response, request.getParameter(
                    "mid"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 5. 获取到Method执行的结果, 通过Response返回回去
//        response.getWriter().write(object.toString());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Data
    @AllArgsConstructor
    public class Handler<T> {
        private T controller;
        private Method method;
        private String url;
    }
}
