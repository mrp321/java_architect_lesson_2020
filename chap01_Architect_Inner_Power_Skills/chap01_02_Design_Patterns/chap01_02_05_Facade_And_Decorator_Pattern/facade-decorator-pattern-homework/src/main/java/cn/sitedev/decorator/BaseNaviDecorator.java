package cn.sitedev.decorator;

/**
 * 基类装饰器
 */
public abstract class BaseNaviDecorator extends NaviBar {
    private NaviBar naviBar;

    public BaseNaviDecorator(NaviBar naviBar) {
        this.naviBar = naviBar;
    }

    @Override
    public String show(boolean isLogin) {
        // 当前功能装饰器的展示情况
        String show = super.show(isLogin);
        // 如果为空, 就不展示
        show = (show == null || show == "") ? "" : " | " + show;
        // 将被装饰的对象与当前功能的装饰器展示情况组合起来
        return naviBar.show(isLogin) + show;
    }
}
