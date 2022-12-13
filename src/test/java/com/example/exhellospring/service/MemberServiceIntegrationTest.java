package com.example.exhellospring.service;

import com.example.exhellospring.domain.Member;
import com.example.exhellospring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("gp");

        //when
        Long joinedId = memberService.join(member);

        //then
        Member findedMember = memberService.findOne(joinedId).get();
        assertThat(member.getName()).isEqualTo(findedMember.getName());
    }

    @Test
    void joinDupMember() {
        //given
        Member member1 = new Member();
        member1.setName("gp");

        Member member2 = new Member();
        member2.setName("gp");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));;

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }

}