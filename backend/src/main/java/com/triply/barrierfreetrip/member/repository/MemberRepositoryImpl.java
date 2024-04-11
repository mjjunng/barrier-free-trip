package com.triply.barrierfreetrip.member.repository;

import com.triply.barrierfreetrip.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class MemberRepositoryImpl implements MemberRepository{
    private final EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        List<Member> members = em.createQuery("select m from Member m " +
                        "where m.email=:emails")
                .setParameter("emails", email)
                .getResultList();
        return members.stream().findAny();
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        List<Member> members = em.createQuery("select m from Member m " +
                        "where m.id=:ids")
                .setParameter("ids", memberId)
                .getResultList();
        return members.stream().findAny();
    }
}
