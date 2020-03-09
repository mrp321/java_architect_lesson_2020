package cn.sitedev.decorator.login;

import cn.sitedev.decorator.BaseNaviDecorator;
import cn.sitedev.decorator.NaviBar;

/**
 * 成长墙功能装饰器
 */
public class WallNaviBarDecorator extends BaseNaviDecorator {

    public WallNaviBarDecorator(NaviBar naviBar) {
        super(naviBar);
    }

    @Override
    public String getName() {
        return "成长墙";
    }

    @Override
    public boolean needLogin() {
        return true;
    }

}
