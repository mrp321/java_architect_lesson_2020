package cn.sitedev.decorator;

import cn.sitedev.decorator.login.ExamNaviBarDecorator;
import cn.sitedev.decorator.login.HomeworkNaviBarDecorator;
import cn.sitedev.decorator.login.WallNaviBarDecorator;
import cn.sitedev.decorator.nologin.*;

/**
 * 测试类
 */
public class NaviBarTest {
    public static void main(String[] args) {
        // 导航栏
        NaviBar naviBar = new BaseNaviBar();

        // 给导航栏装饰各种功能
        naviBar = new QANaviBarDecorator(naviBar);
        naviBar = new ArticleNaviBarDecorator(naviBar);
        naviBar = new HomeworkNaviBarDecorator(naviBar);
        naviBar = new ExamNaviBarDecorator(naviBar);
        naviBar = new WallNaviBarDecorator(naviBar);
        naviBar = new LessonNaviBarDecorator(naviBar);
        naviBar = new BubbleNaviBarDecorator(naviBar);
        naviBar = new MallNaviBarDecorator(naviBar);

        // 未登录情况下, 展示的功能
        System.out.println("noLogin: " + naviBar.show(false));

        // 登陆情况下, 展示的功能
        System.out.println("login: " + naviBar.show(true));
    }
}
