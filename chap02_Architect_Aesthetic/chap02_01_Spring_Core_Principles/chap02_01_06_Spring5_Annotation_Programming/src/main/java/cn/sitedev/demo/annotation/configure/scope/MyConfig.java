package cn.sitedev.demo.annotation.configure.scope;

import cn.sitedev.project.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyConfig {

    /**
     * prototype : 原型, 多例
     * singleton : 单例, 默认
     * request : 主要应用于web模块, 同一次请求只创建一个实例
     * session : 主要应用于web模块, 同一个会话只创建一个实例
     *
     * @return
     */
    @Scope("prototype")
    @Bean("person5")
    public Person person() {
        return new Person("Tom", 21);
    }
}
