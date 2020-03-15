package cn.sitedev.pay;

        import cn.sitedev.pay.payport.PayStrategy;

public class PayTest {
    public static void main(String[] args) {
        Order order = new Order("1", "20200315", 325.4);
        System.out.println(order.pay());
        System.out.println("==================");
        System.out.println(order.pay(PayStrategy.WECHAT_PAY));
    }
}
