package cn.sitedev.strategy.vip.enumeration;

import cn.sitedev.strategy.vip.level.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 会员策略
 */
@Getter
@AllArgsConstructor
public enum StrategyEnum {
    NOT_VIP("NOT_VIP", NotVip.getInstance()), NORMAL_VIP("NORMAL_VIP", NormalVip.getInstance()),
    SILVER_VIP("SILVER_VIP", SilverVip.getInstance()), GOLDEN_VIP("GOLDEN_VIP",
            GoldenVip.getInstance());
    private String key;
    private IVip vip;
}
