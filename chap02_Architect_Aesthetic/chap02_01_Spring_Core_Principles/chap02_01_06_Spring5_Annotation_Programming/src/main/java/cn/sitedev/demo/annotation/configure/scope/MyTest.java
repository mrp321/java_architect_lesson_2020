package cn.sitedev.demo.annotation.configure.scope;

import cn.sitedev.project.entity.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Person person = (Person) context.getBean("person5");
        Person person2 = (Person) context.getBean("person5");
        System.out.println(person == person2);
    }
}
