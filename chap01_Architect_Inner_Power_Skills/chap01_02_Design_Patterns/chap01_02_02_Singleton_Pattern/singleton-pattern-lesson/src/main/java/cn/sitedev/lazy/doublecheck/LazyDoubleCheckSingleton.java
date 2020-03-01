package cn.sitedev.lazy.doublecheck;

/**
 * 双重检测锁单例
 */
public class LazyDoubleCheckSingleton {
    private volatile static LazyDoubleCheckSingleton instance;

    private LazyDoubleCheckSingleton() {
    }

    public static LazyDoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new LazyDoubleCheckSingleton();
                    // 1. 分配内存给这个对象
                    // 2. 初始化对象
                    // 3. 设置instance指向刚分配的内存地址
                    // 4. 初次访问对象
                }
            }
        }
        return instance;
    }
}
