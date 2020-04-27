package cn.sitedev.demo.annotation.injection.primary;

import cn.sitedev.project.dao.MyDao2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Object myDao2 = context.getBean(MyDao2.class);
        System.out.println(myDao2);
    }
}
