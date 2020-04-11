package cn.sitedev.mouseevent;

public class MouseEventTest {
    public static void main(String[] args) {
        MouseEventListener listener = new MouseEventListener();

        Mouse mouse = new Mouse();
        mouse.addListener(MouseEventType.ON_CLICK, listener);
        mouse.addListener(MouseEventType.ON_MOVE, listener);

        mouse.click();
        mouse.move();
        mouse.blur();
        mouse.focus();
    }
}
