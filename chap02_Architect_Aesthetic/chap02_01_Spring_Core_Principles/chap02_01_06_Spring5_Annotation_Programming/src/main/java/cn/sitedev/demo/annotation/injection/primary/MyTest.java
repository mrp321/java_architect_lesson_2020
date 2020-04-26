package cn.sitedev.demo.annotation.injection.primary;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
//        MyService2 myService2 = (MyService2) context.getBean(MyService2.class);
//        myService2.printMyDao2();

        Object myDao2 = context.getBean("myDao2");
        System.out.println(myDao2);

    }
}
