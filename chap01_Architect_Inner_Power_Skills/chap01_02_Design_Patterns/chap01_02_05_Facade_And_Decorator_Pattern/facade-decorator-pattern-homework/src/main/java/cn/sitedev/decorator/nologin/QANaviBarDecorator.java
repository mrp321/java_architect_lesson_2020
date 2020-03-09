package cn.sitedev.decorator.nologin;

import cn.sitedev.decorator.BaseNaviDecorator;
import cn.sitedev.decorator.NaviBar;

/**
 * 问答功能装饰器
 */
public class QANaviBarDecorator extends BaseNaviDecorator {

    public QANaviBarDecorator(NaviBar naviBar) {
        super(naviBar);
    }

    @Override
    public String getName() {
        return "问答";
    }

    @Override
    public boolean needLogin() {
        return false;
    }
}
