package cn.sitedev.cases.optimized;

import cn.sitedev.cases.User;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserService {
    private final ExecutorService single = Executors.newSingleThreadExecutor();

    private volatile boolean isRunning = true;

    private ArrayBlockingQueue<User> arrayBlockingQueue = new ArrayBlockingQueue(10);

    {
        init();
    }

    public static void main(String[] args) {
        new UserService().register();
    }

    private void init() {
        single.execute(() -> {
            while (isRunning) {
                try {
                    // 阻塞式获取数据
                    User user = arrayBlockingQueue.take();
                    sendPoints(user);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean register() {
        User user = new User("Sitedev");
        addUser(user);
        // 添加到异步队列中
        arrayBlockingQueue.add(user);
        return true;
    }

    private void addUser(User user) {
        System.out.println("添加用户: " + user);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendPoints(User user) {
        System.out.println("发送积分给指定用户: " + user);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
