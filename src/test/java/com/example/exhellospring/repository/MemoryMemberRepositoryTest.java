package com.example.exhellospring.repository;

import com.example.exhellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        //given
        Member member = new Member();
        member.setName("gp");

        //when
        repository.save(member);
        Member savedMember = repository.findById(member.getId()).get();

        //then
        assertThat(member).isEqualTo(savedMember);
    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
        //given
        Member member1 = new Member();
        member1.setName("gp1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("gp2");
        repository.save(member2);

        //when
        Member find1 = repository.findByName(member1.getName()).get();
        Member find2 = repository.findByName(member2.getName()).get();

        //then
        assertThat(member1).isEqualTo(find1);
        assertThat(member2).isEqualTo(find2);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member();
        member1.setName("gp1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("gp2");
        repository.save(member2);

        //when
        List<Member> find = repository.findAll();

        //then
        assertThat(find.size()).isEqualTo(2);
    }

}