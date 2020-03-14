package cn.sitedev.login2;


public interface ILoginAdapter {
    boolean support(Object object);

    ResultMsg login(String id, Object adapter);
}
