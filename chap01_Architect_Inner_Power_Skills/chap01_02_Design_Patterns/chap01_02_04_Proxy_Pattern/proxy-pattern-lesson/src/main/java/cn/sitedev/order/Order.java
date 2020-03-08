package cn.sitedev.order;

import lombok.Data;

@Data
public class Order {
    private Object orderInfo;
    private Long createTime;
    private String id;
}
