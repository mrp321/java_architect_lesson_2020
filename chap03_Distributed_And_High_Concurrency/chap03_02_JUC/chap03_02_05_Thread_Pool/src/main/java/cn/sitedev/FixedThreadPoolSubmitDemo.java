package cn.sitedev;

import java.util.concurrent.*;

public class FixedThreadPoolSubmitDemo implements Callable<String> {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future = executorService.submit(new FixedThreadPoolSubmitDemo());
        System.out.println(future.get());
        executorService.shutdown();
    }

    @Override
    public String call() throws Exception {
        return "hello world";
    }
}
