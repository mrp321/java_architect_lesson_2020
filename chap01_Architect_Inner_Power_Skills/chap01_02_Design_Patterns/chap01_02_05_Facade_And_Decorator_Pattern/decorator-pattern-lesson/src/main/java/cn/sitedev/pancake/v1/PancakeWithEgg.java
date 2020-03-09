package cn.sitedev.pancake.v1;

public class PancakeWithEgg extends Pancake {
    @Override
    protected String getMsg() {
        return super.getMsg() + "+1个鸡蛋";
    }

    @Override
    public int getPrice() {
        // 加一个鸡蛋, 多加一块钱
        return super.getPrice() + 1;
    }
}
