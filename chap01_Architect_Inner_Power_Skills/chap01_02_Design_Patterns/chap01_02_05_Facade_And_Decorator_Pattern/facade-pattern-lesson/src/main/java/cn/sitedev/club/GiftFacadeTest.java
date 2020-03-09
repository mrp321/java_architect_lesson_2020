package cn.sitedev.club;

public class GiftFacadeTest {
    public static void main(String[] args) {
        GiftInfo giftInfo = new GiftInfo();
        giftInfo.setName("《Spring5核心原理》");
        GiftFacadeService service = new GiftFacadeService();
        service.exchange(giftInfo);
    }
}
