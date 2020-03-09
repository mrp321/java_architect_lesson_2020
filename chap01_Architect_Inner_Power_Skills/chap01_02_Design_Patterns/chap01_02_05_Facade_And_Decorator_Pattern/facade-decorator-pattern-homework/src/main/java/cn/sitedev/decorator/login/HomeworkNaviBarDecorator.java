package cn.sitedev.decorator.login;

import cn.sitedev.decorator.BaseNaviDecorator;
import cn.sitedev.decorator.NaviBar;

/**
 * 作业功能装饰器
 */
public class HomeworkNaviBarDecorator extends BaseNaviDecorator {

    public HomeworkNaviBarDecorator(NaviBar naviBar) {
        super(naviBar);
    }

    @Override
    public String getName() {
        return "作业";
    }

    @Override
    public boolean needLogin() {
        return true;
    }
}
