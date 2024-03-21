package com.triply.barrierfreetrip.touristfacility.repository;

import com.triply.barrierfreetrip.touristfacility.domain.TouristFacilityHeart;
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
public class TouristFacilityHeartRepositoryImpl implements TouristFacilityHeartRepository {
    private final EntityManager em;

    @Override
    public TouristFacilityHeart findByIds(Member member, TouristFacility touristFacility) {
        return em.createQuery("select h from TouristFacilityHeart h "
                        + "where h.member=:members and h.touristFacility=:tfs", TouristFacilityHeart.class)
                .setParameter("members", member)
                .setParameter("tfs", touristFacility)
                .getSingleResult();
    }

    @Override
    public void save(TouristFacilityHeart touristFacilityHeart) {
        em.persist(touristFacilityHeart);
    }

    @Override
    public int delete(Long hearId) {
        int cnt = em.createQuery("delete from TouristFacilityHeart h where h.id=:ids")
                .setParameter("ids", hearId)
                .executeUpdate();
        em.clear();
        return cnt;
    }

    @Override
    public Optional<TouristFacilityHeart> findByIdsIfLikes(Member member, TouristFacility facility) {
        List<TouristFacilityHeart> touristFacilityHearts = em.createQuery("select h from TouristFacilityHeart h where h.member=:members and h.touristFacility=:facilities"
                        , TouristFacilityHeart.class)
                .setParameter("members", member)
                .setParameter("facilities", facility)
                .getResultList();

        return touristFacilityHearts.stream().findAny();
    }
}
