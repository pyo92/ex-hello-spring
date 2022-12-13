package com.example.exhellospring.service;

import com.example.exhellospring.domain.Member;
import com.example.exhellospring.repository.MemberRepository;
import com.example.exhellospring.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //name 중복 체크
        validateDupMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDupMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
