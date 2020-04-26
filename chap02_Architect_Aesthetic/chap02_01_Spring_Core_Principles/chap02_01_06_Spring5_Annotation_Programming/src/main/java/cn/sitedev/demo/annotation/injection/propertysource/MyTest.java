package cn.sitedev.demo.annotation.injection.propertysource;

import cn.sitedev.project.entity.Bird;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Bird bird = (Bird) context.getBean("bird");
        System.out.println(bird);

        System.out.println("======================从环境变量中取值================");
        Environment environment = context.getEnvironment();
        System.out.println(environment.getProperty("bird.color"));

    }
}
