package cn.sitedev.strategy.vip;

import cn.sitedev.strategy.vip.enumeration.StrategyEnum;
import cn.sitedev.strategy.vip.level.*;

/**
 * VIP策略
 */
public class VipStrategy {
    private VipStrategy() {
    }

    private static class LazyHolder {
        private static final VipStrategy INSTANCE = new VipStrategy();
    }

    public static VipStrategy getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static IVip getStrategy(String strategyKey) {
        IVip vip = null;
        StrategyEnum strategy =
                StrategyEnum.valueOf((strategyKey == null || strategyKey.isEmpty()) ?
                        StrategyEnum.NOT_VIP.getKey() : strategyKey);
        if (strategy == null) {
            vip = StrategyEnum.NOT_VIP.getVip();
        } else {
            vip = strategy.getVip();
        }
        System.out.println("当前采用的会员策略为:" + vip.getName() + ", 折扣为:" + vip.getDiscount());
        return vip;
    }

}
