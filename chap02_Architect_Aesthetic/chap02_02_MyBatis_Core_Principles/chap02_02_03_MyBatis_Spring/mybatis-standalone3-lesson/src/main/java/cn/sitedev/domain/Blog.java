package cn.sitedev.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Blog implements Serializable {
    // 文章id
    private Integer bid;
    // 文章标题
    private String name;
    // 文章作者id
    private Integer authorId;
}
