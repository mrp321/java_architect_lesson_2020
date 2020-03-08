package cn.sitedev.order;

public class OrderService implements IOrderService {
    private OrderDao orderDao;

    public OrderService() {
        // 这里如果使用Spring , 应该是自动注入的
        // 为了使用方便, 我们在构造方法中将orderDao直接初始化
        this.orderDao = new OrderDao();
    }

    @Override
    public int createOrder(Order order) {
        System.out.println("OrderService调用OrderDao创建订单");
        return orderDao.insert(order);
    }
}
