package cn.sitedev.demo.annotation.configure.conditional;

import cn.sitedev.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

public class MyConfig {

    @Bean
    @Conditional(WinCondition.class)
    public Person person() {
        System.out.println("向IOC容器中注入person");
        return new Person();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public Person person2() {
        System.out.println("向IOC容器中注入person2");
        return new Person();
    }

}
