package com.example.barrierfreetrip.caretrip.repository;

import com.example.barrierfreetrip.caretrip.domain.CareTrip;
import com.example.barrierfreetrip.caretrip.domain.CareTripHeart;
import com.example.barrierfreetrip.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CareTripRepositoryImpl implements CareTripRepository{
    private final EntityManager em;
    @Override
    public List<CareTrip> findAll() {
        return em.createQuery("select ct from CareTrip ct")
                .getResultList();
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
