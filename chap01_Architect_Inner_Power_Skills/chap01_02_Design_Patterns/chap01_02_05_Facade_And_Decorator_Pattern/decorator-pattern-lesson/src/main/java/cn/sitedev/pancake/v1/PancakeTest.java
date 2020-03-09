package cn.sitedev.pancake.v1;

public class PancakeTest {
    public static void main(String[] args) {
        Pancake pancake = new Pancake();
        System.out.println(pancake.getMsg() + ", 总价格:" + pancake.getPrice());

        Pancake pancakeWithEgg = new PancakeWithEgg();
        System.out.println(pancakeWithEgg.getMsg() + ", 总价格:" + pancakeWithEgg.getPrice());

        Pancake pancakeWithEggAndSausage = new PancakeWithEggAndSausage();
        System.out.println(pancakeWithEggAndSausage.getMsg() + ", 总价格:" + pancakeWithEggAndSausage.getPrice());
    }
}
