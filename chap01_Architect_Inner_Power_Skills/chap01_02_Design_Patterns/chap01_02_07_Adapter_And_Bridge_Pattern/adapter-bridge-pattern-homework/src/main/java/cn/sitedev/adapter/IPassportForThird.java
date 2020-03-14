package cn.sitedev.adapter;

import cn.sitedev.adapter.adapters.ILoginAdapter;

public interface IPassportForThird {
    ResultMsg login(String id, Class<? extends ILoginAdapter> adapterClazz);

}
