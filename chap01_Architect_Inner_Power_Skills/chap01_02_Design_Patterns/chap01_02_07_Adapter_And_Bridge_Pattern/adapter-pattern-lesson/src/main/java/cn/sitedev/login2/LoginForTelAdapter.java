package cn.sitedev.login2;

public class LoginForTelAdapter extends AbstractAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForTelAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return super.login4Regist(id, null);
    }
}
