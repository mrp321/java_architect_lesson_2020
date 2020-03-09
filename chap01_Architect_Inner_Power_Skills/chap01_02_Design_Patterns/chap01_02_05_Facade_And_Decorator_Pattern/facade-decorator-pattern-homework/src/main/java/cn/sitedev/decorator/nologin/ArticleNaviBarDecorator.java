package cn.sitedev.decorator.nologin;

import cn.sitedev.decorator.BaseNaviDecorator;
import cn.sitedev.decorator.NaviBar;

/**
 * 文章功能装饰器
 */
public class ArticleNaviBarDecorator extends BaseNaviDecorator {

    public ArticleNaviBarDecorator(NaviBar naviBar) {
        super(naviBar);
    }

    @Override
    public String getName() {
        return "文章";
    }

    @Override
    public boolean needLogin() {
        return false;
    }

}
