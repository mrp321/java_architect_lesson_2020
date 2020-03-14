package cn.sitedev.login2;

public class LoginForWechatAdapter extends AbstractAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForWechatAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return login4Regist(id, null);
    }
}
