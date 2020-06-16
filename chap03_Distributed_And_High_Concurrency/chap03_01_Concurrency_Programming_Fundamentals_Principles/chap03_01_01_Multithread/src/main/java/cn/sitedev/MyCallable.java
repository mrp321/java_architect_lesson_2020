package cn.sitedev;

import java.util.concurrent.*;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        int a = 1;
        int b = 2;
        System.out.println(a + b);
        return "执行结果:" + (a + b);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(myCallable);
        System.out.println(future.get());
        executorService.shutdown();
    }
}
