package cn.sitedev.login2;

public class AbstractAdapter extends PassportService implements ILoginAdapter {
    @Override
    public boolean support(Object adapter) {
        throw new UnsupportedOperationException("暂不支持该方法");
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        throw new UnsupportedOperationException("暂不支持该方法");
    }

    protected ResultMsg login4Regist(String username, String password) {
        if (password == null) {
            password = "THIRD_EMPTY";
        }
        super.regist(username, password);
        return super.login(username, password);
    }
}
