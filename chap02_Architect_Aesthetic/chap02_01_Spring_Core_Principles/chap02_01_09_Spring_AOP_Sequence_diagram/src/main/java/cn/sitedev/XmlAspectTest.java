package cn.sitedev;

import cn.sitedev.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlAspectTest {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-application-xml.xml");
        MemberService memberService = ctx.getBean(MemberService.class);
        System.out.println("=========================");
        memberService.get(12345);
        System.out.println("=========================");
        memberService.get();
        System.out.println("=========================");
        memberService.save();
        System.out.println("=========================");
        memberService.delete();
        System.out.println("=========================");
    }
}
