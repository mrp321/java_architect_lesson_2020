package cn.sitedev.factorymethod;

/**
 * 支付工厂接口
 */
public interface IPaymentFactory {

    /**
     * 创建支付方式
     *
     * @return
     */
    IPayment createPayment();
}
