package cn.sitedev.simplefactory;

/**
 * 跨境支付方式
 */
public class CrossBorderPayment implements IPayment {
    @Override
    public void pay() {
        System.out.println("跨境支付");
    }
}
