package cn.sitedev.auth.chainbuilder;

import cn.sitedev.auth.Member;

public class ValidateHandler extends Handler {
    @Override
    public void doHandle(Member member) {
        if (member.getLoginName() == null || member.getLoginName().isEmpty() || member.getLoginPass() == null || member.getLoginPass().isEmpty()) {
            System.out.println("用户名或密码为空");
            return;
        }
        System.out.println("用户名和密码校验成功, 可以往下执行");
        chain.doHandle(member);
    }
}
