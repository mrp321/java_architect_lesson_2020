package cn.sitedev.factorymethod;

/**
 * 支付宝支付方式
 */
public class AlipayPayment implements IPayment {
    @Override
    public void pay() {
        System.out.println("支付宝支付");
    }
}
