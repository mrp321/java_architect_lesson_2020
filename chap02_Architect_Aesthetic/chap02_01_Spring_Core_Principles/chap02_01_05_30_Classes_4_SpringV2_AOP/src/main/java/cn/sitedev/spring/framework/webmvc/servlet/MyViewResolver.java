package cn.sitedev.spring.framework.webmvc.servlet;

import java.io.File;

/**
 * 视图解析器
 */
public class MyViewResolver {
    private static final String DEFAULT_TEMPLATE_SUFFIX = ".html";

    private File templateRootDir;

    public MyViewResolver(String templateRoot) {
        String templateRootPath =
                this.getClass().getClassLoader().getResource(templateRoot).getFile();
        this.templateRootDir = new File(templateRootPath);
    }

    public MyView resolveViewName(String viewName) {
        if (viewName == null || "".equals(viewName.trim())) {
            return null;
        }
        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX) ? viewName :
                (viewName + DEFAULT_TEMPLATE_SUFFIX);
        File templateFile =
                new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+", "/"));
        return new MyView(templateFile);
    }
}
