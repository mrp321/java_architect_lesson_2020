package cn.sitedev.strategy.vip.entity;

import cn.sitedev.strategy.vip.VipStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String id;
    private double price;

    public double pay(String vipType) {
        System.out.println("开始支付订单:" + this.id + ", 原价格为:" + this.price);
        double discountPrice = VipStrategy.getStrategy(vipType).getDiscount() * this.price;
        System.out.println("支付订单成功:" + this.id + ", 折扣后价格为:" + discountPrice);
        return discountPrice;
    }

    public double pay() {
        return this.pay(null);
    }
}
