package cn.sitedev.auth.chainbuilder;

import cn.sitedev.auth.Member;

public class AuthHandler extends Handler {
    @Override
    public void doHandle(Member member) {
        if (!"管理员".equals(member.getRoleName())) {
            System.out.println("您不是管理员, 没有操作权限");
            return;
        }
        System.out.println("您是管理员, 允许操作");
    }
}
