package cn.sitedev.demo.annotation.configure.lazy;

import cn.sitedev.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class MyConfig {
    @Bean
    public Person person() {
        System.out.println("将对象person添加到IOC容器中");
        return new Person("Tom", 26);
    }

    /**
     * 默认是非延时加载的
     * 延时加载,懒加载, 只针对单例Bean起作用
     * 默认容器启动时不创建对象, 调用对象的功能时才创建
     *
     * @return
     */
    @Lazy
    @Bean
    public Person person2() {
        System.out.println("将对象person2添加到IOC容器中");
        return new Person("Tom", 27);
    }
}
