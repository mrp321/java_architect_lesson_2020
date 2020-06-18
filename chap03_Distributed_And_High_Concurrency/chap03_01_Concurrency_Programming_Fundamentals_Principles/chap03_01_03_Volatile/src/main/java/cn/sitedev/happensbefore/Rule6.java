package cn.sitedev.happensbefore;

public class Rule6 {
    public int x = 10;

    public void monitor() {
        synchronized (this) { // 此处自动加锁
            // x是共享变量, 初始值为10
            if (this.x < 12) {
                this.x = 12;
            }
        } // 此处自动解锁
    }

}
