package cn.sitedev.promotion;

public class PromotionTest {
    public static void main(String[] args) {
        PromotionActivity activity = new PromotionActivity(new CouponStrategy());
        activity.execute();
    }
}
