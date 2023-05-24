package com.example.barrierfreetrip.heart.repository;

import com.example.barrierfreetrip.heart.domain.Heart;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional
public class HeartRepositoryImpl implements HeartRepository{
    private final EntityManager em;

    @Override
    public Heart findByIds(Member member, TouristFacility touristFacility) {
        return em.createQuery("select h from Heart h "
                        + "where h.member=:members and h.touristFacility=:tfs", Heart.class)
                .setParameter("members", member)
                .setParameter("tfs", touristFacility)
                .getSingleResult();
    }

    @Override
    public void save(Heart heart) {
        em.persist(heart);
    }

    @Override
    public int delete(Long hearId) {
        int cnt = em.createQuery("delete from Heart h where h.id=:ids")
                .setParameter("ids", hearId)
                .executeUpdate();
        em.clear();
        return cnt;
    }
}
