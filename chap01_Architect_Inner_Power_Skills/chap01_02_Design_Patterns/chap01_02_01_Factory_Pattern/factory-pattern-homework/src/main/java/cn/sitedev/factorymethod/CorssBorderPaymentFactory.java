package cn.sitedev.factorymethod;

/**
 * 跨境支付支付方式工厂
 */
public class CorssBorderPaymentFactory implements IPaymentFactory {
    @Override
    public IPayment createPayment() {
        return new CrossBorderPayment();
    }
}
