package cn.sitedev.container.threadsafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器式单例演示(线程安全)
 */
public class ContainerSingleton {

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    private ContainerSingleton() {
    }

    /**
     * 线程安全
     *
     * @param className
     * @return
     */
    public static Object getBean(String className) {
        // 将IOC容器作为锁
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                Object object = null;
                try {
                    object = Class.forName(className).newInstance();
                    ioc.put(className, object);
                    return object;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                return ioc.get(className);
            }
        }
        return null;
    }
}
