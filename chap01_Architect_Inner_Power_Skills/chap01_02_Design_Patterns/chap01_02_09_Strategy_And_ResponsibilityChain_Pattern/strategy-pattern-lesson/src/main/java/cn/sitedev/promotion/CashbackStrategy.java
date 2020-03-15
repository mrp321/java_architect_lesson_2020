package cn.sitedev.promotion;

public class CashbackStrategy implements IPromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("使用返现");
    }
}
