package cn.sitedev.controller;

import cn.sitedev.entity.Member;
import cn.sitedev.entity.Result;
import cn.sitedev.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/query")
    public Result<List<Member>> query(Member member) {
        List<Member> members = this.memberService.query(member);
        return new Result<>("0", "查询成功", members);
    }

    @PostMapping("add")
    public Result<Member> add(Member member) {
        this.memberService.add(member);
        return new Result<>("0", "添加成功", member);
    }

    @PutMapping("modify")
    public Result<Member> modify(Long id, Member member) {
        this.memberService.modify(id, member);
        return new Result<>("0", "修改成功", member);
    }

    @DeleteMapping("delete")
    public Result<Member> delete(Member member) {
        this.memberService.delete(member);
        return new Result<>("0", "删除成功", member);
    }
}
