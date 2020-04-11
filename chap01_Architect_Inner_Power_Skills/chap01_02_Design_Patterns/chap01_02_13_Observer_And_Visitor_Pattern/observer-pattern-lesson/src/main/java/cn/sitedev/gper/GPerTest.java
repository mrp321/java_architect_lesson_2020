package cn.sitedev.gper;

public class GPerTest {
    public static void main(String[] args) {
        GPer gPer = GPer.getInstance();
        Teacher teacher = new Teacher("张三");
        Teacher teacher2 = new Teacher("李四");
        gPer.addObserver(teacher);
        gPer.addObserver(teacher2);

        // 业务逻辑代码
        Question question = new Question();
        question.setUserName("小明");
        question.setContent("观察者适用于哪些场景?");

        gPer.publishQuestion(question);
    }
}
