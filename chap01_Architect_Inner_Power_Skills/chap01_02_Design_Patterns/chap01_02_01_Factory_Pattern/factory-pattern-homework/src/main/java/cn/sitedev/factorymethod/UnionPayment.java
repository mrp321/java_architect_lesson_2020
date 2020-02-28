package cn.sitedev.factorymethod;

/**
 * 银联支付方式
 */
public class UnionPayment implements IPayment {
    @Override
    public void pay() {
        System.out.println("银联支付");
    }
}
