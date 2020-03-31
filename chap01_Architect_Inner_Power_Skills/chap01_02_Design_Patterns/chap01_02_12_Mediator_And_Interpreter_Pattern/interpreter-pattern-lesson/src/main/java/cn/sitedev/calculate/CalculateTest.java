package cn.sitedev.calculate;

public class CalculateTest {
    public static void main(String[] args) {
        System.out.println("10+30=" + new MyCalculator("10 + 30").calculate());
        System.out.println("10-30=" + new MyCalculator("10 - 30").calculate());
        System.out.println("10*30=" + new MyCalculator("10 * 30").calculate());
        System.out.println("10/30=" + new MyCalculator("10 / 30").calculate());
    }
}
