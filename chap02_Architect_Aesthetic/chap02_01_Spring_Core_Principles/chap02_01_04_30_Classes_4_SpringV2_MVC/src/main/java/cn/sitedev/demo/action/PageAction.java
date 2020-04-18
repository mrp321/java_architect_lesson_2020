package cn.sitedev.demo.action;

import cn.sitedev.demo.service.IQueryService;
import cn.sitedev.spring.framework.annotation.MyAutowired;
import cn.sitedev.spring.framework.annotation.MyController;
import cn.sitedev.spring.framework.annotation.MyRequestMapping;
import cn.sitedev.spring.framework.annotation.MyRequestParam;
import cn.sitedev.spring.framework.webmvc.servlet.MyModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 公布接口url
 */
@MyController
@MyRequestMapping("/")
public class PageAction {
    @MyAutowired
    private IQueryService queryService;

    @MyRequestMapping("/first.html")
    public MyModelAndView query(@MyRequestParam("teacher") String teacher) {
        String result = queryService.query(teacher);
        Map<String, Object> model = new HashMap<>();
        model.put("teacher", teacher);
        model.put("data", result);
        model.put("token", "123456");
        return new MyModelAndView("first.html", model);
    }
}
