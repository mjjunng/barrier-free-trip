package com.triply.barrierfreetrip.heart.repository;

import com.triply.barrierfreetrip.heart.domain.TouristHeart;
import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;
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
    public TouristHeart findByIds(Member member, TouristFacility touristFacility) {
        return em.createQuery("select h from TouristHeart h "
                        + "where h.member=:members and h.touristFacility=:tfs", TouristHeart.class)
                .setParameter("members", member)
                .setParameter("tfs", touristFacility)
                .getSingleResult();
    }

    @Override
    public void save(TouristHeart touristHeart) {
        em.persist(touristHeart);
    }

    @Override
    public int delete(Long hearId) {
        int cnt = em.createQuery("delete from TouristHeart h where h.id=:ids")
                .setParameter("ids", hearId)
                .executeUpdate();
        em.clear();
        return cnt;
    }

    @Override
    public Optional<TouristHeart> findByIdsIfLikes(Member member, TouristFacility facility) {
        List<TouristHeart> touristHearts = em.createQuery("select h from TouristHeart h where h.member=:members and h.touristFacility=:facilities"
                        , TouristHeart.class)
                .setParameter("members", member)
                .setParameter("facilities", facility)
                .getResultList();

        return touristHearts.stream().findAny();
    }
}
