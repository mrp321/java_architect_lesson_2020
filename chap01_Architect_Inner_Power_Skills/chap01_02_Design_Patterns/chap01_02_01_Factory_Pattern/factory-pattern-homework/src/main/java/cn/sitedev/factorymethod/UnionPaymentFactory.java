package cn.sitedev.factorymethod;

/**
 * 银联支付方式工厂
 */
public class UnionPaymentFactory implements IPaymentFactory {
    @Override
    public IPayment createPayment() {
        return new UnionPayment();
    }
}
