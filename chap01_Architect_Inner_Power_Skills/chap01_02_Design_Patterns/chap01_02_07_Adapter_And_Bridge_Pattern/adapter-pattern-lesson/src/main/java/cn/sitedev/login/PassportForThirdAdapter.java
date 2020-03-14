package cn.sitedev.login;

public class PassportForThirdAdapter extends PassportService implements IPassportForThird {
    @Override
    public ResultMsg login4QQ(String openid) {
        return login4Regist(openid, null);
    }

    @Override
    public ResultMsg login4Wechat(String openid) {
        return login4Regist(openid, null);
    }

    @Override
    public ResultMsg login4Token(String token) {
        return login4Regist(token, null);
    }

    @Override
    public ResultMsg login4Telephone(String phone, String code) {
        return login4Regist(phone, null);
    }

    private ResultMsg login4Regist(String username, String password) {
        if (password == null) {
            password = "THIRD_EMPTY";
        }
        super.regist(username, password);
        return super.login(username, password);
    }
}
