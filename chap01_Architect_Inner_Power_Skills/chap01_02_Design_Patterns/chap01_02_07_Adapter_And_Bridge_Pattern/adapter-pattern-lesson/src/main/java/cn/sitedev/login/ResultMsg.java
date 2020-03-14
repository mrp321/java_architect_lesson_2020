package cn.sitedev.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMsg {
    private int code;
    private String msg;
    private Object data;
}
