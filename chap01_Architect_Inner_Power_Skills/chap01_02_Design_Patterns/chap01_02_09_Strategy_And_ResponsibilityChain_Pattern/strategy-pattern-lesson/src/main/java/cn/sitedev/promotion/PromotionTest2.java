package cn.sitedev.promotion;

public class PromotionTest2 {
    public static void main(String[] args) {
        PromotionActivity activity = null;
        String promotionKey = "COUPON";

        if ("COUPON".equals(promotionKey)) {
            activity = new PromotionActivity(new CouponStrategy());
        } else if ("CASHBACK".equals(promotionKey)) {
            activity = new PromotionActivity(new CashbackStrategy());
        } else if ("GROUPBUY".equals(promotionKey)) {
            activity = new PromotionActivity(new GroupbuyStrategy());
        } else {
            activity = new PromotionActivity(new EmptyStrategy());
        }

        activity.execute();
    }
}
