package cn.sitedev.promotion.improved;

import cn.sitedev.promotion.IPromotionStrategy;

public class PromotionImprovedTest {
    public static void main(String[] args) {
        PromotionStrategyFactory.getPromotionKeys();

        String promotionKey = "COUPON";

        IPromotionStrategy strategy = PromotionStrategyFactory.getPromotionStrategy(promotionKey);
        strategy.doPromotion();
    }
}
