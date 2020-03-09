package cn.sitedev.pancake.v1;

public class PancakeWithEggAndSausage extends PancakeWithEgg {
    @Override
    protected String getMsg() {
        return super.getMsg() + "1根香肠";
    }

    @Override
    public int getPrice() {
        // 加1根香肠, 加2块钱
        return super.getPrice() + 2;
    }
}
