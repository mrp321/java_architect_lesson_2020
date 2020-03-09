package cn.sitedev.club;

public class QualityService {
    public boolean isAvailable(GiftInfo giftInfo) {
        System.out.println("校验" + giftInfo.getName() + "积分资格通过, 库存通过");
        return true;
    }
}
