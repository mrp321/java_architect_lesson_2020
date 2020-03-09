package cn.sitedev.club;

public class GiftFacadeService {
    private QualityService qualityService = new QualityService();
    private PaymentService paymentService = new PaymentService();
    private LogisticsService logisticsService = new LogisticsService();

    // 兑换
    public void exchange(GiftInfo giftInfo) {
        if (qualityService.isAvailable(giftInfo)) {
            // 资格校验通过
            if (paymentService.pay(giftInfo)) {
                // 如果支付积分成功
                String orderNo = logisticsService.delivery(giftInfo);
                System.out.println("物流系统下单成功, 订单号是:" + orderNo);
            }
        }
    }
}
