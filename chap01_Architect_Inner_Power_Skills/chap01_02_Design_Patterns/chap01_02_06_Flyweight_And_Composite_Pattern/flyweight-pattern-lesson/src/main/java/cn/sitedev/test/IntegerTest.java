package cn.sitedev.test;

public class IntegerTest {
    public static void main(String[] args) {
        Integer a = Integer.valueOf(127);
        Integer b = 127;

        Integer c = Integer.valueOf(128);
        Integer d = 128;

        System.out.println("a == b ? " + (a == b));
        System.out.println("c == d ? " + (c == d));

    }
}
