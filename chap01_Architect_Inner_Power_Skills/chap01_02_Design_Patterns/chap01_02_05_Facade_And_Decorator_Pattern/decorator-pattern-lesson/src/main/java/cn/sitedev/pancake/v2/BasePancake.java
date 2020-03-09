package cn.sitedev.pancake.v2;

public class BasePancake extends Pancake {
    @Override
    protected String getMsg() {
        return "煎饼";
    }

    @Override
    protected int getPrice() {
        return 5;
    }
}
