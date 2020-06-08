package cn.sitedev.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private Integer bid; // 文章id
    private String name; // 文章标题
    private String authorId; // 文章作者id
}
