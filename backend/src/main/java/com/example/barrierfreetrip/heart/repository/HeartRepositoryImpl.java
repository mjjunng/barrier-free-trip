package com.example.barrierfreetrip.heart.repository;

import com.example.barrierfreetrip.heart.domain.Heart;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.touristfacility.domain.TouristFacility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Heart> findByIdsIfLikes(Member member, TouristFacility facility) {
        List<Heart> hearts = em.createQuery("select h from Heart h where h.member=:members and h.touristFacility=:facilities"
                        , Heart.class)
                .setParameter("members", member)
                .setParameter("facilities", facility)
                .getResultList();

        return hearts.stream().findAny();
    }
}
