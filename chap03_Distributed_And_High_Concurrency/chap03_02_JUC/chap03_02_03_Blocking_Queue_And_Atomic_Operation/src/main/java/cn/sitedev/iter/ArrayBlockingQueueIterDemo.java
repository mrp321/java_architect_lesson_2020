package cn.sitedev.iter;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueIterDemo {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(5);
        for (int i = 0; i < 5; i++) {
            arrayBlockingQueue.add("element" + i);
        }

        Iterator<String> iterator = arrayBlockingQueue.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
