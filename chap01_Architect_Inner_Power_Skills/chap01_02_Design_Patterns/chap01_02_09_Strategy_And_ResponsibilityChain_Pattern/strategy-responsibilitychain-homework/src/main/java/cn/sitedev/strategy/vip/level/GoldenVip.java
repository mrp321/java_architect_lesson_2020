package cn.sitedev.strategy.vip.level;

/**
 * 金牌会员
 */
public class GoldenVip implements IVip {

    private GoldenVip() {
    }

    public static GoldenVip getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final GoldenVip INSTANCE = new GoldenVip();
    }

    @Override
    public String getName() {
        return "金牌会员";
    }

    @Override
    public double getDiscount() {
        return 0.8;
    }

}
