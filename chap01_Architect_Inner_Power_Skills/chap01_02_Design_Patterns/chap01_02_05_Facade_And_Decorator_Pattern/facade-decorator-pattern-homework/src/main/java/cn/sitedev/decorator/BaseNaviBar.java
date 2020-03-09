package cn.sitedev.decorator;

/**
 * 导航栏抽象实现
 */
public class BaseNaviBar extends NaviBar {

    @Override
    public String getName() {
        return "导航栏";
    }

    @Override
    public boolean needLogin() {
        return false;
    }
}
