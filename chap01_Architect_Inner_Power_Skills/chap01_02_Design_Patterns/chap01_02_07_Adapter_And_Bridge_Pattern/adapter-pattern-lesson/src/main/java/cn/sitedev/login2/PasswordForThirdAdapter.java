package cn.sitedev.login2;

public class PasswordForThirdAdapter implements IPassportForThird {
    @Override
    public ResultMsg login4QQ(String openid) {
        return processLogin(openid, LoginForQQAdapter.class);
    }

    @Override
    public ResultMsg login4Wechat(String openid) {
        return processLogin(openid, LoginForWechatAdapter.class);
    }

    @Override
    public ResultMsg login4Token(String token) {
        return processLogin(token, LoginForTokenAdapter.class);
    }

    @Override
    public ResultMsg login4Telephone(String phone, String code) {
        return processLogin(phone, LoginForTelAdapter.class);
    }

    private ResultMsg processLogin(String id, Class<? extends ILoginAdapter> clazz) {
        try {
            ILoginAdapter adapter = clazz.newInstance();
            if (adapter.support(adapter)) {
                return adapter.login(id, adapter);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
