package cn.sitedev.demo.annotation.configure.imports;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }


        System.out.println("===========================");
        Object monkey = context.getBean("monkey");
        System.out.println("monkey = " + monkey);
        Object monkey2 = context.getBean("&monkey");
        System.out.println("&monkey = " + monkey2);
    }
}
