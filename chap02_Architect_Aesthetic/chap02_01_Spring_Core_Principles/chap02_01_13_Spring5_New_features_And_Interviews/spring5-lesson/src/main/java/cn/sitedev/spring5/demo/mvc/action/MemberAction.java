package cn.sitedev.spring5.demo.mvc.action;

import cn.sitedev.spring5.demo.model.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class MemberAction {

    @GetMapping("/getByName.json")
    public Mono<Member> getByName(@RequestParam String name) {
        Member member = new Member();
        member.setName(name);
        return Mono.justOrEmpty(member);
    }

    @GetMapping("/getAll.json")
    public Flux<Member> preview(HttpServletRequest request, HttpServletResponse response) {
        return Flux.fromArray(new Member[]{new Member("Sitedev1"), new Member("Sitedev2")});
    }

    @PostMapping("/remove.json")
    public Mono<Object> remove(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @GetMapping("/edit.json")
    public Flux<Object> edit(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
