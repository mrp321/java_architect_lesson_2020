package cn.sitedev.factorymethod;

public class FactoryMethodTest {
    public static void main(String[] args) {
        IPaymentFactory factory = new UnionPaymentFactory();
        factory.createPayment().pay();
    }
}
