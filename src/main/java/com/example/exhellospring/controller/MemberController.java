package com.example.exhellospring.controller;

import com.example.exhellospring.domain.Member;
import com.example.exhellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String newMember() {
        return "members/newMember";
    }

    /**

     @PostMapping("/members/new")
     public String joinMember(@RequestParam(name = "name") String name) {
         Member member = new Member();
         member.setName(name);
         memberService.join(member);

         return "redirect:/";
     }

     // RequestParam 많을 경우, 별도 클래스로 전달 받아 처리

     */
    @PostMapping("/members/new")
    public String joinMember(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String listMember(Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "members/listMember";
    }

}
