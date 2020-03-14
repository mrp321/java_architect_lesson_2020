package cn.sitedev.adapter;

import cn.sitedev.adapter.adapters.LoginForSinaAdapter;

public class LoginAdapterTest {
    public static void main(String[] args) {
        IPassportForThird adapter = new PasswordForThirdAdapter();
        adapter.login("sitedev", LoginForSinaAdapter.class);
    }
}
