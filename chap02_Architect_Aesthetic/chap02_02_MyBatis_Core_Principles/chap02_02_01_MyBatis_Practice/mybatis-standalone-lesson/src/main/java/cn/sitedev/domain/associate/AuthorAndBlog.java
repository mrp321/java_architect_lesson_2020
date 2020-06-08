package cn.sitedev.domain.associate;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuthorAndBlog implements Serializable {
    // 作者id
    private Integer author_id;
    // 作者名称
    private String author_name;
    // 文章和评论列表
    List<BlogAndComment> blog;
}
