package cn.sitedev.strategy.vip.level;

/**
 * 普通会员
 */
public class NormalVip implements IVip {
    private NormalVip() {
    }

    public static NormalVip getInstance() {
        return NormalVip.LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final NormalVip INSTANCE = new NormalVip();
    }

    @Override
    public String getName() {
        return "普通会员";
    }

    @Override
    public double getDiscount() {
        return 0.9;
    }
}
