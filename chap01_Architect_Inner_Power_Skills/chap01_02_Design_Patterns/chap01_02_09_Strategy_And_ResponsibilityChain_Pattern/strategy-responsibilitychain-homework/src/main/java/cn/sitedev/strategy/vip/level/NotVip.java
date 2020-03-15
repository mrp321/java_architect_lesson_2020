package cn.sitedev.strategy.vip.level;

/**
 * 非会员
 */
public class NotVip implements IVip {
    private NotVip() {
    }

    public static NotVip getInstance() {
        return NotVip.LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final NotVip INSTANCE = new NotVip();
    }

    @Override
    public String getName() {
        return "非会员";
    }

    @Override
    public double getDiscount() {
        return 1;
    }
}
