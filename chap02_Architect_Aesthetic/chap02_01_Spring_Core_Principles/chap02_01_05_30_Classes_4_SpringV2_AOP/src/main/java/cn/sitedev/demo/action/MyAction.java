package cn.sitedev.demo.action;

import cn.sitedev.demo.service.IModifyService;
import cn.sitedev.demo.service.IQueryService;
import cn.sitedev.spring.framework.annotation.MyAutowired;
import cn.sitedev.spring.framework.annotation.MyController;
import cn.sitedev.spring.framework.annotation.MyRequestMapping;
import cn.sitedev.spring.framework.annotation.MyRequestParam;
import cn.sitedev.spring.framework.webmvc.servlet.MyModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 公布接口url
 */
@MyController
@MyRequestMapping("/web")
public class MyAction {
    @MyAutowired
    private IQueryService queryService;

    @MyAutowired
    private IModifyService modifyService;

    @MyRequestMapping("/query.json")
    public MyModelAndView query(HttpServletRequest request, HttpServletResponse response,
                                @MyRequestParam("name") String name) {
        String result = queryService.query(name);
        return out(response, result);
    }

    @MyRequestMapping("/add*.json")
    public MyModelAndView add(HttpServletRequest request, HttpServletResponse response,
                              @MyRequestParam("name") String name,
                              @MyRequestParam("addr") String addr) {
        try {
            String result = modifyService.add(name, addr);
            return out(response, result);
        } catch (Throwable e) {
            Map<String, String> model = new HashMap<String, String>();
            model.put("detail", e.getCause().getMessage());
            model.put("stackTrace", Arrays.toString(e.getStackTrace()));
            return new MyModelAndView("500", model);
        }

    }

    @MyRequestMapping("/remove.json")
    public MyModelAndView remve(HttpServletRequest request, HttpServletResponse response,
                                @MyRequestParam("id") Integer id) {
        String result = modifyService.remove(id);
        return out(response, result);
    }

    @MyRequestMapping("edit.json")
    public MyModelAndView edit(HttpServletRequest request, HttpServletResponse response,
                               @MyRequestParam("id") Integer id,
                               @MyRequestParam("name") String name) {
        String result = modifyService.edit(id, name);
        return out(response, result);
    }

    private MyModelAndView out(HttpServletResponse response, String str) {
        try {
            response.getWriter().write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
