package cn.sitedev.spring.framework.webmvc.servlet;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * 模型与视图
 */
@Data
@AllArgsConstructor
public class MyModelAndView {
    private String viewName;
    private Map<String, ?> model;

    public MyModelAndView(String viewName) {
        this.viewName = viewName;
    }
}
