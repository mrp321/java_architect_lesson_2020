package cn.sitedev.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Author implements Serializable {
    // 作者id
    private Integer authorId;
    // 作者名称
    private String authorName;
}
