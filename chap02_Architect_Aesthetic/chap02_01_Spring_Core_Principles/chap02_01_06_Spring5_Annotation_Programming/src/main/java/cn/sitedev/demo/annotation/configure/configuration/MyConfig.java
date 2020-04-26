package cn.sitedev.demo.annotation.configure.configuration;

import cn.sitedev.project.entity.Man;
import cn.sitedev.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    // 默认是取类名首字母小写
    // 其次取方法的名称
    // 最后取bean注解的value
    @Bean
    public Person person2() {
        return new Person("Tom", 19);
    }

    @Bean("person3")
    public Person person1() {
        return new Person("Tom", 20);
    }

    @Bean
    public Man man() {
        return new Man("Tom", 21);
    }
}
