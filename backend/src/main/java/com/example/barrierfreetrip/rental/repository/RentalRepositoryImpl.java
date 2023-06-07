package com.example.barrierfreetrip.rental.repository;

import com.example.barrierfreetrip.rental.domain.Rental;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RentalRepositoryImpl implements RentalRepository{
    private final EntityManager em;

    @Override
    public List<Rental> findByAreaName(String sido, String sigungu) {
        return em.createQuery("select r from Rental r where r.sido=:sidos and r.sigungu=:sigungus")
                .setParameter("sidos", sido)
                .setParameter("sigungus", sigungu)
                .getResultList();
    }

    @Override
    public Optional<Rental> findById(Long id) {
        List<Rental> rentals = em.createQuery("select r from Rental r where r.id=:ids")
                .setParameter("ids", id)
                .getResultList();

        return rentals.stream().findAny();
    }

    @Override
    public Optional<Rental> findByTitle(String keyword) {
        List<Rental> rentals = em.createQuery("select r from Rental r " +
                        "where r.title like concat('%',:keywords,'%') ")
                .setParameter("keywords", keyword)
                .getResultList();

        return rentals.stream().findAny();
    }
}
