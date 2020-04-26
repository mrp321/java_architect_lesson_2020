package cn.sitedev.demo.annotation.configure.lazy;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("IOC容器创建完成");
        context.getBean("person");
        context.getBean("person2");

    }
}
