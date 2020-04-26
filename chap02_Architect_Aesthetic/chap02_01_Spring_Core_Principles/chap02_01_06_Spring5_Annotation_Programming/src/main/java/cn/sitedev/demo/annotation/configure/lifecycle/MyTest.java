package cn.sitedev.demo.annotation.configure.lifecycle;

import cn.sitedev.project.entity.Car;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void test() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("IOC容器创建完成");
        Car car = context.getBean(Car.class);
        car.run();
        context.close();
    }
}
