package cn.sitedev.adapter.adapters;

import cn.sitedev.adapter.ResultMsg;

public class LoginForQQAdapter extends AbstractAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForQQAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        if (!support(adapter)) {
            return null;
        }
        return super.login4Regist(id, null);
    }
}
