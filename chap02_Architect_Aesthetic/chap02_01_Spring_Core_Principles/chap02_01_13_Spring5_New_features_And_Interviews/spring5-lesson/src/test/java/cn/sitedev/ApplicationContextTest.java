package cn.sitedev;

import cn.sitedev.spring5.demo.RouterConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(RouterConfig.class);
        Object instance = app.getBean("timerRouter2");
        System.out.println(instance);
    }
}
