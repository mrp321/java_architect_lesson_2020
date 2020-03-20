package cn.sitedev.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int id;
    private OrderStatus status;

    @Override
    public String toString() {
        return "Order{" + "订单号:" + id + ", 订单状态:" + status + '}';
    }
}
