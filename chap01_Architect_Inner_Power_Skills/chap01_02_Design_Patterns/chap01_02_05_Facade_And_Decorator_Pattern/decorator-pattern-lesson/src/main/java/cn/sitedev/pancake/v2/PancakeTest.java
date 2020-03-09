package cn.sitedev.pancake.v2;

public class PancakeTest {
    public static void main(String[] args) {
        // 购买1个煎饼
        Pancake pancake = new BasePancake();

        // 加一个鸡蛋
        pancake = new EggDecorator(pancake);

        // 再加一个鸡蛋
        pancake = new EggDecorator(pancake);

        // 加根香肠
        pancake = new SausageDecorator(pancake);

        //跟静态代理最大区别就是职责不同
        //静态代理不一定要满足 is-a 的关系
        //静态代理会做功能增强，同一个职责变得不一样

        // 装饰器更多考虑的是扩展
        System.out.println(pancake.getMsg() + ", 总价:" + pancake.getPrice());
    }
}
