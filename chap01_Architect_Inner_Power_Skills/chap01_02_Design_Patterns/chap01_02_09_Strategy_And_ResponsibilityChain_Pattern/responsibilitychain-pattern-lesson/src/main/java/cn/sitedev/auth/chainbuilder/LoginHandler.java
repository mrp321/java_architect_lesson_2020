package cn.sitedev.auth.chainbuilder;

import cn.sitedev.auth.Member;

public class LoginHandler extends Handler {
    @Override
    public void doHandle(Member member) {
        System.out.println("登陆成功");
        member.setRoleName("管理员");
        chain.doHandle(member);
    }
}
