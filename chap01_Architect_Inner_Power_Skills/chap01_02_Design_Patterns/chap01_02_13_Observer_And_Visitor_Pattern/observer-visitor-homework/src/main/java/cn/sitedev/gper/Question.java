package cn.sitedev.gper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 主题
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String userName;
    private String content;
}
