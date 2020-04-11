package cn.sitedev.mouseevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 标准事件源格式的定义
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    // 事件源, 动作是由谁发出的
    private Object source;
    // 事件触发, 要通知谁(观察者)
    private EventListener target;
    // 观察者给的回应
    private Method callback;
    // 事件的名称
    private String trigger;
    // 事件的触发事件
    private long time;

    public Event(EventListener target, Method callback) {
        this.target = target;
        this.callback = callback;
    }

    public Event setSource(Object source) {
        this.source = source;
        return this;
    }

    public Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    public Event setTime(long time) {
        this.time = time;
        return this;
    }
}
