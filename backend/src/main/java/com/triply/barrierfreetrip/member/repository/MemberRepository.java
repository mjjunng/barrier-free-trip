package com.triply.barrierfreetrip.member.repository;

import com.triply.barrierfreetrip.member.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findById(Long memberId);

}
