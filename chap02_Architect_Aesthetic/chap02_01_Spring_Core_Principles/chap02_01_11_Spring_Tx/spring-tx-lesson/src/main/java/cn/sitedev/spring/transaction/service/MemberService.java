package cn.sitedev.spring.transaction.service;

import cn.sitedev.spring.transaction.dao.MemberDao;
import cn.sitedev.spring.transaction.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;

    public List<Member> queryAll() throws Exception {
        return this.memberDao.selectAll();
    }

    public boolean add(Member member) throws Exception {
        boolean flag = this.memberDao.insert(member);
        throwEx(true);
        return flag;
    }

    public boolean remove(long id) throws Exception {
        boolean flag = this.memberDao.delete(id);
        throwEx(true);
        return flag;
    }

    public boolean modify(long id, String name) throws Exception {
        return this.memberDao.update(id, name);
    }

    public boolean login(long id, String name) throws Exception {
        boolean flag = this.modify(id, name);
        throwEx(true);
        return flag;
    }

    private void throwEx(boolean throwEx) throws Exception {
        if (throwEx) {
            throw new Exception("手动抛出异常");
        }
    }
}
