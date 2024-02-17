package com.triply.barrierfreetrip.caretrip.repository;

import com.triply.barrierfreetrip.caretrip.domain.CareTrip;
import com.triply.barrierfreetrip.caretrip.domain.CareTripHeart;
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
public class CareTripHeartRepositoryImpl implements CareTripHeartRepository{
    private final EntityManager em;

    @Override
    public void save(CareTripHeart careTripHeart) {
        em.persist(careTripHeart);
    }

    @Override
    public int delete(Long heartId) {
        int cnt = em.createQuery("delete from CareTripHeart ch where ch.id=:ids")
                .setParameter("ids", heartId)
                .executeUpdate();
        em.clear();
        return cnt;
    }

    @Override
    public Optional<CareTripHeart> findByIds(Member member, CareTrip careTrip) {
        List <CareTripHeart> careTripHearts = em.createQuery("select cth from CareTripHeart cth " +
                        "where cth.member=:members and cth.careTrip=:careTrips")
                .setParameter("members", member)
                .setParameter("careTrips", careTrip)
                .getResultList();

        return careTripHearts.stream().findAny();
    }

    @Override
    public Optional<CareTripHeart> findByIdsIfLikes(Member member, CareTrip careTrip) {
        List <CareTripHeart> careTripHearts = em.createQuery("select cth from CareTripHeart cth " +
                        "where cth.member=:members and cth.careTrip=:careTrips")
                .setParameter("members", member)
                .setParameter("careTrips", careTrip)
                .getResultList();

        return careTripHearts.stream().findAny();
    }


}
