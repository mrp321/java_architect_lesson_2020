package cn.sitedev.strategy.vip;

import cn.sitedev.strategy.vip.entity.Order;
import cn.sitedev.strategy.vip.enumeration.StrategyEnum;

import java.util.UUID;

public class VipTest {
    public static void main(String[] args) {
        Order order = new Order(UUID.randomUUID().toString(), 200);
        order.pay();
        System.out.println("============================");

        order.pay(StrategyEnum.GOLDEN_VIP.getKey());
    }
}
