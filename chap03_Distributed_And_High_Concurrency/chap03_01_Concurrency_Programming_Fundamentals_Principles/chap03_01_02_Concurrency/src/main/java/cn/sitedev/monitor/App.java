package cn.sitedev.monitor;

public class App {
    public static void main(String[] args) {
        synchronized (App.class) {

        }
        test();
    }

    public static synchronized void test() {

    }
}
