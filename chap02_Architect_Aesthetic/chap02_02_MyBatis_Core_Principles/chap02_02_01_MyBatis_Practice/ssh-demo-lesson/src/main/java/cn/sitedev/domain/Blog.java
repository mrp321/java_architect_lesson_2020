package cn.sitedev.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "blog")
@Data
public class Blog {
    @Id
    @Column(name = "bid")
    private Integer bid;

    @Column(name = "name")
    private String name;

    @Column(name = "author_id")
    private Integer authorId;
}