package cn.sitedev.login;

public class PassportService {
    /**
     * 注册
     *
     * @param username
     * @param password
     * @return
     */
    public ResultMsg regist(String username, String password) {
        return new ResultMsg(200, "注册成功", new Member());
    }

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return
     */
    public ResultMsg login(String username, String password) {
        return null;
    }
}
