package cn.sitedev.hungry;

/**
 * 饿汉式静态代码块单例
 */
public class HungrySingletonWithStaticBlock {
    private static final HungrySingletonWithStaticBlock INSTANCE;

    static {
        INSTANCE = new HungrySingletonWithStaticBlock();
    }

    private HungrySingletonWithStaticBlock() {
    }

    public static HungrySingletonWithStaticBlock getInstance() {
        return INSTANCE;
    }

}
