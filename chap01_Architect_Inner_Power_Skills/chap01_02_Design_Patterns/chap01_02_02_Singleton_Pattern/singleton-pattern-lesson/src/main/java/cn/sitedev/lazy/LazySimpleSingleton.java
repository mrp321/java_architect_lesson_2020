package cn.sitedev.lazy;

/**
 * 懒汉式单例模式在外部需要使用的时候才进行实例化
 */
public class LazySimpleSingleton {
    private static LazySimpleSingleton instance;

    private LazySimpleSingleton() {
    }

    public static LazySimpleSingleton getInstance() {
        if (instance == null) {
            instance = new LazySimpleSingleton();
        }
        return instance;
    }
}
