package cn.sitedev.promotion.improved;

import cn.sitedev.promotion.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PromotionStrategyFactory {
    private static Map<String, IPromotionStrategy> promotionStrategyMap = new HashMap<>();

    private static final IPromotionStrategy EMPTY = new EmptyStrategy();

    private PromotionStrategyFactory() {
    }

    static {
        promotionStrategyMap.put(PromotionKey.COUPON, new CouponStrategy());
        promotionStrategyMap.put(PromotionKey.CASHBACK, new CashbackStrategy());
        promotionStrategyMap.put(PromotionKey.GROUPBUY, new GroupbuyStrategy());
    }

    private interface PromotionKey {
        String COUPON = "COUPON";
        String CASHBACK = "CASHBACK";
        String GROUPBUY = "GROUPBUY";
    }

    public static IPromotionStrategy getPromotionStrategy(String promotionKey) {
        IPromotionStrategy strategy = promotionStrategyMap.get(promotionKey);
        return strategy == null ? EMPTY : strategy;
    }

    public static Set<String> getPromotionKeys() {
        return promotionStrategyMap.keySet();
    }
}
