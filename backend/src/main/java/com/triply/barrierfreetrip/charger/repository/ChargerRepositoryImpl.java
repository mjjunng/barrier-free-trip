package com.triply.barrierfreetrip.charger.repository;

import com.triply.barrierfreetrip.charger.domain.Charger;
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

    @Override
    public List<Charger> findNearChargersByPos(Double userX, Double userY, double dis) {
        return em.createQuery("select c from Charger c " +
                        "where 6371 * acos( cos( radians( :userYs ) ) * cos( radians( c.mapy ) ) " +
                        "* cos( radians( c.mapx ) - radians(:userXs) ) " +
                        "+ sin( radians(:userYs) ) * sin( radians(c.mapy) ) ) < :diss")
                .setParameter("userYs", userY)
                .setParameter("userXs", userX)
                .setParameter("diss", dis)
                .getResultList();


    }
}
