package cn.sitedev.adapter.adapters;

import cn.sitedev.adapter.ResultMsg;

public class LoginForSinaAdapter extends AbstractAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForSinaAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return super.login4Regist(id, null);
    }
}
