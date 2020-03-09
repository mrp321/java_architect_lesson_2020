package cn.sitedev.decorator.nologin;

import cn.sitedev.decorator.BaseNaviDecorator;
import cn.sitedev.decorator.NaviBar;

/**
 * 精品课功能装饰器
 */
public class LessonNaviBarDecorator extends BaseNaviDecorator {

    public LessonNaviBarDecorator(NaviBar naviBar) {
        super(naviBar);
    }

    @Override
    public String getName() {
        return "精品课";
    }

    @Override
    public boolean needLogin() {
        return false;
    }
}
