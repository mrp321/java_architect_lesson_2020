package cn.sitedev.factorymethod;

/**
 * 微信支付方式工厂
 */
public class WechatPaymentFactory implements IPaymentFactory {
    @Override
    public IPayment createPayment() {
        return new WechatPayment();
    }
}
