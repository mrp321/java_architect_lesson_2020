package cn.sitedev.spring.transaction.service;

import cn.sitedev.spring.transaction.entity.Member;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(locations = {"classpath*:application-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberServiceTest {
    @Autowired
    private MemberService memberService;

    @Test
    public void queryAll() {
        List<Member> list = null;
        try {
            list = memberService.queryAll();
            System.out.println(JSON.toJSONString(list, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemove() {
        try {
            boolean flag = memberService.remove(1L);
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogin() {
        try {
            memberService.login(1L, "sitedevvvvvv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAdd() {
        Member member = new Member("sitedev", "China", 18);
        try {
            memberService.add(member);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


