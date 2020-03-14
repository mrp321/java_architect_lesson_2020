package cn.sitedev.login;

public interface IPassportForThird {
    ResultMsg login4QQ(String openid);

    ResultMsg login4Wechat(String openid);

    ResultMsg login4Token(String token);

    ResultMsg login4Telephone(String phone, String code);
}
