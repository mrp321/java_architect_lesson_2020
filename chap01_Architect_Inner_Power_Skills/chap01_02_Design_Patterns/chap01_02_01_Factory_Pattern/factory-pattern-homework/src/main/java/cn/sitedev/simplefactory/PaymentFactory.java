package cn.sitedev.simplefactory;

public class PaymentFactory {
    /**
     * 创建支付方式
     *
     * @param paymentType 支付方式
     * @return
     */
    IPayment createPayment(String paymentType) {
        IPayment payment = null;
        switch (paymentType) {
            case "alipay":
                payment = new AlipayPayment();
                break;
            case "crossborder":
                payment = new CrossBorderPayment();
                break;
            case "unionpay":
                payment = new UnionPayment();
                break;
            case "wechat":
                payment = new WechatPayment();
                break;
            default:
                break;
        }
        return payment;
    }
}
