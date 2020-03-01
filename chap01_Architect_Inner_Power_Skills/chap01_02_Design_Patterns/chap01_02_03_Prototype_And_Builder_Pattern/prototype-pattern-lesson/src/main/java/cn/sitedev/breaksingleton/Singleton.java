package cn.sitedev.breaksingleton;

public class Singleton implements Cloneable {
    private static final Singleton INSTANCE = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return INSTANCE;
    }
}
