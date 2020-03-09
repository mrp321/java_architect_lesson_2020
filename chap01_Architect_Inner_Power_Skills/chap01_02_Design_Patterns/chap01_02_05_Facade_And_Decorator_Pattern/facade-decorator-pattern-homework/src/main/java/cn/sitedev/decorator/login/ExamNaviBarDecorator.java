package cn.sitedev.decorator.login;

import cn.sitedev.decorator.BaseNaviDecorator;
import cn.sitedev.decorator.NaviBar;

/**
 * 题库功能装饰器
 */
public class ExamNaviBarDecorator extends BaseNaviDecorator {

    public ExamNaviBarDecorator(NaviBar naviBar) {
        super(naviBar);
    }

    @Override
    public String getName() {
        return "题库";
    }

    @Override
    public boolean needLogin() {
        return true;
    }

}
