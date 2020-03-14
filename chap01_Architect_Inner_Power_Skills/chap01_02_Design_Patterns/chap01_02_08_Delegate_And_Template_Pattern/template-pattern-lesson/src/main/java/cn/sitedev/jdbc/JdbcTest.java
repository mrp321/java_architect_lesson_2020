package cn.sitedev.jdbc;

import cn.sitedev.jdbc.dao.MemberDao;

import java.util.List;

public class JdbcTest {
    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao(null);
        List<?> result = memberDao.selectAll();
        System.out.println(result);
    }
}
