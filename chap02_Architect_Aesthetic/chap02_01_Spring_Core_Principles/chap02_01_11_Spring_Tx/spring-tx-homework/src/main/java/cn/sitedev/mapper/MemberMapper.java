package cn.sitedev.mapper;

import cn.sitedev.entity.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberMapper {
    List<Member> query(Member member);

    void add(Member member);

    void modify(@Param("id") Long id, @Param("member") Member member);

    void delete(Member member);
}
