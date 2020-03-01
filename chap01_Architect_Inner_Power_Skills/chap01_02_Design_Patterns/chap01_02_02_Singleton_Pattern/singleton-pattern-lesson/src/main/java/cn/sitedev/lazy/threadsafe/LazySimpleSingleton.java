package cn.sitedev.lazy.threadsafe;

/**
 * 懒汉式单例模式(线程安全)
 */
public class LazySimpleSingleton {
    private static LazySimpleSingleton instance;

    private LazySimpleSingleton() {
    }

    public static synchronized LazySimpleSingleton getInstance() {
        if (instance == null) {
            instance = new LazySimpleSingleton();
        }
        return instance;
    }
}
