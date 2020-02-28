package cn.sitedev.simplefactory;

/**
 * 微信支付方式
 */
public class WechatPayment implements IPayment {
    @Override
    public void pay() {
        System.out.println("微信支付");
    }
}
