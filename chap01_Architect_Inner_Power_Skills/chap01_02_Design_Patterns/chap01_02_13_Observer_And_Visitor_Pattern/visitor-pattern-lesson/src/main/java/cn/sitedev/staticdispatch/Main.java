package cn.sitedev.staticdispatch;

public class Main {
    public void test(String string) {
        System.out.println("string");
    }

    public void test(Integer integer) {
        System.out.println("integer");
    }

    public static void main(String[] args) {
        String string = "1";
        Integer integer = 1;

        Main main = new Main();

        main.test(string);
        main.test(integer);
    }
}
