package cn.sitedev.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Comment implements Serializable {
    // 评论id
    private Integer commentId;
    // 所属文章id
    private Integer bid;
    // 内容
    private String content;
}
