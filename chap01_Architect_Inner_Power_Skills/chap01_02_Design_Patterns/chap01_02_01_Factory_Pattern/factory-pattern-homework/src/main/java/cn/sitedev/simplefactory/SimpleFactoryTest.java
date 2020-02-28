package cn.sitedev.simplefactory;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        PaymentFactory factory = new PaymentFactory();
        factory.createPayment("alipay").pay();
    }
}
