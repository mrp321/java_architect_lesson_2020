package cn.sitedev.memento;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleMemento {
    private String title;
    private String content;
    private String imgs;
}
