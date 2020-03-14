package cn.sitedev.mvc;

import cn.sitedev.mvc.controllers.MemberController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    private Map<String, Method> handlerMapping = new HashMap<>();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatch(req, resp);
    }

    @Override
    public void init() throws ServletException {
        try {
            handlerMapping.put("web/getMemberById.json", MemberController.class.getMethod(
                    "getMemberById", new Class[]{String.class}));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) {
        String url = request.getRequestURI();
        Method method = handlerMapping.get(url);
//        method.invoke();
    }
}
