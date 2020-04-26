package cn.sitedev.demo.annotation.configure.configuration;

import cn.sitedev.project.entity.Man;
import cn.sitedev.project.entity.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Person person2 = (Person) context.getBean("person2");
        Person person3 = (Person) context.getBean("person3");

        System.out.println(person2);
        System.out.println(person3);

        Man man = (Man) context.getBean(Man.class);
        System.out.println(man);
    }
}
