package com.triply.barrierfreetrip.member.repository;

import com.triply.barrierfreetrip.member.domain.Member;

import java.util.Optional;

public interface MemberRepository{
    void save(Member member);
    Optional<Member> findByEmail(String email);

    Optional<Member> findById(Long memberId);
}
