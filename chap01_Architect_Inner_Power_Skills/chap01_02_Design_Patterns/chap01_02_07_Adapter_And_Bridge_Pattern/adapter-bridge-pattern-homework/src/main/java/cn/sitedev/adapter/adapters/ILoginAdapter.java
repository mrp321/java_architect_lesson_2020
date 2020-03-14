package cn.sitedev.adapter.adapters;


import cn.sitedev.adapter.ResultMsg;

public interface ILoginAdapter {
    boolean support(Object object);

    ResultMsg login(String id, Object adapter);
}
