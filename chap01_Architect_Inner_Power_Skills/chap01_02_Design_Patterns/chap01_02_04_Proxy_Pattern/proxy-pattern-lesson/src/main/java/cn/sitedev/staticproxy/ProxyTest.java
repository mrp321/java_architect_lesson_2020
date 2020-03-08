package cn.sitedev.staticproxy;

public class ProxyTest {
    public static void main(String[] args) {
        ZhangLaosan zhangLaosan = new ZhangLaosan(new Zhangsan());
        zhangLaosan.findLove();
    }
}
