package cn.sitedev.adapter;

import cn.sitedev.adapter.adapters.*;

public class PasswordForThirdAdapter implements IPassportForThird {

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

    @Override
    public ResultMsg login(String id, Class<? extends ILoginAdapter> adapterClazz) {
        return this.processLogin(id, adapterClazz);
    }
}
