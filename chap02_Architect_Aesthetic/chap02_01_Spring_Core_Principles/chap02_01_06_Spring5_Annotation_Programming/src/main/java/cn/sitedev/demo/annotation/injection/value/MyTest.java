package cn.sitedev.demo.annotation.injection.value;

import cn.sitedev.project.entity.Bird;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Bird bird = (Bird) context.getBean("bird");
        System.out.println(bird);

    }
}
