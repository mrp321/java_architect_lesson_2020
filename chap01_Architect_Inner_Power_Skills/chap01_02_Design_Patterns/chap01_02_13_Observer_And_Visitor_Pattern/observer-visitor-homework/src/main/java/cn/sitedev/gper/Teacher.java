package cn.sitedev.gper;

import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 观察者
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private String name;

    /**
     * 订阅
     *
     * @param question
     */
    @Subscribe
    public void subscribe(Question question) {
        System.out.println("=====================================");
        System.out.println(this.name + "老师, 您好.\n您收到了一个提问, 希望您解答. " + "问题内容如下:\n" + question.getContent() + "\n提问者:" + question.getUserName());
        System.out.println("=====================================");
    }
}
