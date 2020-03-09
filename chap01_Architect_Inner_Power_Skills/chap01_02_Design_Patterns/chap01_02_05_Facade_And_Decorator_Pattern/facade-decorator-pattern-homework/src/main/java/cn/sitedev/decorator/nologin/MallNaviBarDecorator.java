package cn.sitedev.decorator.nologin;

import cn.sitedev.decorator.BaseNaviDecorator;
import cn.sitedev.decorator.NaviBar;

/**
 * 商城功能装饰器
 */
public class MallNaviBarDecorator extends BaseNaviDecorator {

    public MallNaviBarDecorator(NaviBar naviBar) {
        super(naviBar);
    }

    @Override
    public String getName() {
        return "商城";
    }

    @Override
    public boolean needLogin() {
        return false;
    }

}
