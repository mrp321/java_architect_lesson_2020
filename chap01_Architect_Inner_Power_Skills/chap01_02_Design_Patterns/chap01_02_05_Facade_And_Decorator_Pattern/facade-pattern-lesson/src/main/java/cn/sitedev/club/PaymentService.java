package cn.sitedev.club;

public class PaymentService {
    public boolean pay(GiftInfo giftInfo) {
        //扣减积分
        System.out.println("支付" + giftInfo.getName() + " 积分成功");
        return true;
    }
}
