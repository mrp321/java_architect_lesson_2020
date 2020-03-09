package cn.sitedev.decorator;

/**
 * 导航栏抽象
 */
public abstract class NaviBar {
    /**
     * 名称
     *
     * @return
     */
    public abstract String getName();

    /**
     * 是否需要登陆才能使用
     *
     * @return
     */
    public abstract boolean needLogin();

    /**
     * 展示功能
     *
     * @param isLogin 是否登陆
     * @return
     */
    public String show(boolean isLogin) {
        // 如果当前功能需要登陆 && 当前是登陆状态 || 当前功能不需要登陆
        if (needLogin() && isLogin || !needLogin()) {
            return getName();
        }
        return "";
    }
}
