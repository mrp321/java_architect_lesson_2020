package cn.sitedev.factorymethod;

/**
 * 支付宝支付方式工厂
 */
public class AlipayPaymentFactory implements IPaymentFactory {
    @Override
    public IPayment createPayment() {
        return new AlipayPayment();
    }
}
