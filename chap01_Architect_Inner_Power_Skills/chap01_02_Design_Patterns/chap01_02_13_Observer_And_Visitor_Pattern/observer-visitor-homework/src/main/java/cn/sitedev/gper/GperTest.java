package cn.sitedev.gper;

import com.google.common.eventbus.EventBus;

public class GperTest {
    public static void main(String[] args) {
        // 要提交的事件
        Question question = new Question("小明", "观察者适用于哪些场景?");

        // 观察者
        Teacher teacher1 = new Teacher("张三");
        Teacher teacher2 = new Teacher("李四");


        EventBus eventBus = new EventBus();

        // 注册观察者
        eventBus.register(teacher1);
        eventBus.register(teacher2);
        // 提交事件
        eventBus.post(question);
    }
}
