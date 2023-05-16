package com.example.barrierfreetrip.member.repository;

import com.example.barrierfreetrip.member.domain.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {

    Member findByEmail(String email);
}
