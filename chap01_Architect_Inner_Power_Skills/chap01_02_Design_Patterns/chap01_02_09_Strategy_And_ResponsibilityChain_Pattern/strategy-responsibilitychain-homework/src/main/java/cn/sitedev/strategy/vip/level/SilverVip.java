package cn.sitedev.strategy.vip.level;

/**
 * 银牌会员
 */
public class SilverVip implements IVip {

    private SilverVip() {
    }

    public static SilverVip getInstance() {
        return SilverVip.LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final SilverVip INSTANCE = new SilverVip();
    }

    @Override
    public String getName() {
        return "银牌会员";
    }

    @Override
    public double getDiscount() {
        return 0.85;
    }
}
