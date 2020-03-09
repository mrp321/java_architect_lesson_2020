package cn.sitedev.club;

public class LogisticsService {
    // 发货
    public String delivery(GiftInfo giftInfo) {
        // 物流系统的对接逻辑
        System.out.println(giftInfo.getName() + "进入物流系统");
        String orderNo = "666";
        return orderNo;
    }
}
