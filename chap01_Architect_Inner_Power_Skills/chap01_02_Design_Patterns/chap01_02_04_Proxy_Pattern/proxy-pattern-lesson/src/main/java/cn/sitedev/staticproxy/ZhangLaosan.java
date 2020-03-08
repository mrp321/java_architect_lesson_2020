package cn.sitedev.staticproxy;

public class ZhangLaosan implements IPerson {
    private Zhangsan zhangsan;

    public ZhangLaosan(Zhangsan zhangsan) {
        this.zhangsan = zhangsan;
    }

    @Override
    public void findLove() {
        System.out.println("张老三开始物色");
        this.zhangsan.findLove();
        System.out.println("开始交往");
    }
}
