package cn.sitedev.pay.payport;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付策略管理
 */
public class PayStrategy {

    public static final String ALI_PAY = "AliPay";
    public static final String JD_PAY = "JdPay";
    public static final String WECHAT_PAY = "WechatPay";
    private static final String UNION_PAY = "UnionPay";
    public static final String DEFAULT_PAY = ALI_PAY;

    private static Map<String, Payment> strategyMap = new HashMap<>();

    static {
        strategyMap.put(ALI_PAY, new AliPay());
        strategyMap.put(JD_PAY, new JDPay());
        strategyMap.put(WECHAT_PAY, new WechatPay());
        strategyMap.put(UNION_PAY, new UnionPay());
    }

    public static Payment get(String payKey) {
        if (strategyMap.containsKey(payKey)) {
            return strategyMap.get(payKey);
        }
        return strategyMap.get(DEFAULT_PAY);
    }

}
