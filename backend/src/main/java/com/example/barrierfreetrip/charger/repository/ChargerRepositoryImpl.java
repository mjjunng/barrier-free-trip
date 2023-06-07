package com.example.barrierfreetrip.charger.repository;

import com.example.barrierfreetrip.charger.domain.Charger;
import com.example.barrierfreetrip.rental.domain.Rental;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChargerRepositoryImpl implements ChargerRepository{
    private final EntityManager em;

    public List<Charger> findByAreaCode(String areaCode) {
        return em.createQuery("select c from Charger c where c.areaCode=:areaCodes")
                .setParameter("areaCodes", areaCode)
                .getResultList();
    }

    @Override
    public Optional<Charger> findById(Long id) {
        List charger = em.createQuery("select c from Charger c where c.id=:ids")
                .setParameter("ids", id)
                .getResultList();

        return charger.stream().findAny();
    }

    @Override
    public Optional<Charger> findByTitle(String keyword) {
        List<Charger> chargers = em.createQuery("select c from Charger c " +
                        "where c.title like concat('%',:keywords,'%') ")
                .setParameter("keywords", keyword)
                .getResultList();

        return chargers.stream().findAny();
    }
}
