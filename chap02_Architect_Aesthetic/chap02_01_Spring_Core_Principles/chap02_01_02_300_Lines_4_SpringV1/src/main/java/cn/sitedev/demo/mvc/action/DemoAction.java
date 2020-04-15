package cn.sitedev.demo.mvc.action;

import cn.sitedev.demo.service.IDemoService;
import cn.sitedev.mvcframework.annotation.MyAutowired;
import cn.sitedev.mvcframework.annotation.MyController;
import cn.sitedev.mvcframework.annotation.MyRequestMapping;
import cn.sitedev.mvcframework.annotation.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MyController
@MyRequestMapping("/demo")
public class DemoAction {
    @MyAutowired
    private IDemoService demoService;

    @MyRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response, @MyRequestParam(
            "name") String name, @MyRequestParam("id") String id) {
        String result = demoService.get(name);
        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response,
                    @MyRequestParam("a") Integer a, @MyRequestParam("b") Integer b) {
        try {
            response.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/remove")
    public void remove(HttpServletRequest request, HttpServletResponse response, @MyRequestParam(
            "id") Integer id) {
    }

}
