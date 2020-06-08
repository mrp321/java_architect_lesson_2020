package cn.sitedev.domain.associate;

import cn.sitedev.domain.Author;
import lombok.Data;

import java.io.Serializable;

@Data
public class BlogAndAuthor implements Serializable {
    // 文章id
    private Integer bid;
    // 文档标题
    private String name;
    // 作者
    private Author author;
}
