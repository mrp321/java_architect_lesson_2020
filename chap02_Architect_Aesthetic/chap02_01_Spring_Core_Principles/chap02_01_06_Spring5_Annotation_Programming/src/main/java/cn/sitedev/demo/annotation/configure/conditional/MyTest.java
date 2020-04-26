package cn.sitedev.demo.annotation.configure.conditional;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {

    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println("IOC容器初始化完成");
        // 假设当前操作系统如果是windows, 则加载person, 如果是 Linux,则加载person2

    }
}
