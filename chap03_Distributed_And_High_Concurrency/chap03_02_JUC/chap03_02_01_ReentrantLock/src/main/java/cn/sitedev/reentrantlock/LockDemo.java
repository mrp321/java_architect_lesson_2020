package cn.sitedev.reentrantlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockDemo {
    private static Map<String, Object> cacheMap = new HashMap<>();
    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = rwl.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = rwl.writeLock();

    public static final Object get(String key) {
        System.out.println("开始读数据");
        readLock.lock(); // 读锁
        try {
            return cacheMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public static final Object put(String key, Object value) {
        writeLock.lock();
        System.out.println("开始写数据");
        try {
            return cacheMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
}
