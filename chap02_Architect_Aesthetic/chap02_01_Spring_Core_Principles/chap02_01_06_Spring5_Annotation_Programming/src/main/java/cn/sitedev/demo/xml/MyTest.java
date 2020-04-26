package cn.sitedev.demo.xml;

import cn.sitedev.project.entity.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        Person person = context.getBean(Person.class);
        System.out.println(person);
    }
}
