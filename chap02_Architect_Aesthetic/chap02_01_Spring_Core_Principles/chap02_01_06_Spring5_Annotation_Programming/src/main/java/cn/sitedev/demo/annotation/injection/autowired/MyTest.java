package cn.sitedev.demo.annotation.injection.autowired;

import cn.sitedev.project.dao.MyDao;
import cn.sitedev.project.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        MyService service = (MyService) context.getBean("myService");
        service.printMyDao();

        MyDao dao = (MyDao) context.getBean("myDao");
        System.out.println(dao);


    }
}
