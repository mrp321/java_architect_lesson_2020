package cn.sitedev.promotion;

public class GroupbuyStrategy implements IPromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("使用团购优惠");
    }
}
