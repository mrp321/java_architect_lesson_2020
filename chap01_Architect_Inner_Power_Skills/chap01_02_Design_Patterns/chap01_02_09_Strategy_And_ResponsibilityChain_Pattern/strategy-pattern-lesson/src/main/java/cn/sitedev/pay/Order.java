package cn.sitedev.pay;

import cn.sitedev.pay.payport.PayStrategy;
import cn.sitedev.pay.payport.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String uid;
    private String orderId;
    private double amount;

    public MsgResult pay() {
        return pay(PayStrategy.DEFAULT_PAY);
    }

    public MsgResult pay(String payKey) {
        Payment payment = PayStrategy.get(payKey);
        System.out.println("欢迎使用:" + payment.getName());
        System.out.println("本地交易金额为:" + amount + ", 开始扣款");
        return payment.pay(uid, amount);
    }
}
