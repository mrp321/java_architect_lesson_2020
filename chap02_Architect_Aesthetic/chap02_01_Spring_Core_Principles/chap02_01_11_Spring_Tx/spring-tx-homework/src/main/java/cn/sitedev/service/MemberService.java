package cn.sitedev.service;

import cn.sitedev.entity.Member;
import cn.sitedev.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {SQLException.class, Exception.class})
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;


    public List<Member> query(Member member) {
        return memberMapper.query(member);

    }

    public void add(Member member) {
        memberMapper.add(member);
        int i = 10 / 0;

    }

    public void modify(Long id, Member member) {
        memberMapper.modify(id, member);
    }

    public void delete(Member member) {
        memberMapper.delete(member);
    }


}
