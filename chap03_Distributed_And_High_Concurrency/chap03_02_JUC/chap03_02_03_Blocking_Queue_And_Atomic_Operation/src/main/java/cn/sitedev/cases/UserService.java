package cn.sitedev.cases;

public class UserService {
    public static void main(String[] args) {
        new UserService().register();
    }

    public boolean register() {
        User user = new User("Sitedev");
        addUser(user);
        sendPoints(user);
        return true;
    }

    private void addUser(User user) {
        System.out.println("添加用户: " + user);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sendPoints(User user) {
        System.out.println("发送积分给指定用户: " + user);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
