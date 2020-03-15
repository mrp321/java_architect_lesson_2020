package cn.sitedev.promotion;

public class PromotionActivity {
    private IPromotionStrategy strategy;

    public PromotionActivity(IPromotionStrategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        this.strategy.doPromotion();
    }
}
