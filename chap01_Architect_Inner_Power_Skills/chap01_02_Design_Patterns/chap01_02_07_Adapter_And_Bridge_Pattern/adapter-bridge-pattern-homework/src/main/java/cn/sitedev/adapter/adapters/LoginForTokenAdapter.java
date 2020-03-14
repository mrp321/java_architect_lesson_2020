package cn.sitedev.adapter.adapters;

import cn.sitedev.adapter.ResultMsg;

public class LoginForTokenAdapter extends AbstractAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForTokenAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return super.login4Regist(id, null);
    }
}
