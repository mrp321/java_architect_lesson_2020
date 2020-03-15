package cn.sitedev.pay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 支付完成以后的状态
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgResult {
    private int code;
    private String msg;
    private Object data;

}
