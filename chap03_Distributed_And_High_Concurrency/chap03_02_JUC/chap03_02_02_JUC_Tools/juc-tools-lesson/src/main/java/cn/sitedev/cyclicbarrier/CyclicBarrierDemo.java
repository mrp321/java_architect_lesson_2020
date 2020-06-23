package cn.sitedev.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo extends Thread {

    @Override
    public void run() {
        System.out.println("开始进行数据分析");
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new CyclicBarrierDemo());

        new Thread(new DataImportThread(cyclicBarrier, "file1")).start();
        new Thread(new DataImportThread(cyclicBarrier, "file2")).start();
        new Thread(new DataImportThread(cyclicBarrier, "file3")).start();
    }
}
