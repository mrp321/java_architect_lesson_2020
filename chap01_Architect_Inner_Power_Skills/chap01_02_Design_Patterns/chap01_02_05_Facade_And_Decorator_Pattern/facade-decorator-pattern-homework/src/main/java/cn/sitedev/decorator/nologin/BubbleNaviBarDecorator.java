package cn.sitedev.decorator.nologin;

import cn.sitedev.decorator.BaseNaviDecorator;
import cn.sitedev.decorator.NaviBar;

/**
 * 冒泡功能装饰器
 */
public class BubbleNaviBarDecorator extends BaseNaviDecorator {

    public BubbleNaviBarDecorator(NaviBar naviBar) {
        super(naviBar);
    }

    @Override
    public String getName() {
        return "冒泡";
    }

    @Override
    public boolean needLogin() {
        return false;
    }

}
