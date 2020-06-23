package cn.sitedev.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConditionSignal implements Runnable {
    private Lock lock;
    private Condition condition;

    public ConditionSignal(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        System.out.println("begin: ConditionSignal");
        lock.lock();
        try {
            condition.signal();
            System.out.println("end: ConditionSignal");
        } finally {
            lock.unlock();
        }
    }
}
