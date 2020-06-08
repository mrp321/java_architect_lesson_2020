package cn.sitedev.domain.associate;

import cn.sitedev.domain.Comment;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BlogAndComment implements Serializable {
    // 文章id
    private Integer bid;
    // 文章标题
    private String name;
    // 文章作者id
    private Integer authorId;
    // 文章评论
    private List<Comment> comment;
}
