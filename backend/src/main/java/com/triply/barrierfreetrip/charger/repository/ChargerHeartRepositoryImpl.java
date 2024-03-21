package com.triply.barrierfreetrip.charger.repository;

import com.triply.barrierfreetrip.charger.domain.Charger;
import com.triply.barrierfreetrip.charger.domain.ChargerHeart;
import com.triply.barrierfreetrip.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class ChargerHeartRepositoryImpl implements ChargerHeartRepository{
    private final EntityManager em;

    @Override
    public void save(ChargerHeart chargerHeart) {
        em.persist(chargerHeart);
    }

    @Override
    public int delete(Long heartId) {
        int cnt = em.createQuery("delete from ChargerHeart ch where ch.id=:ids")
                .setParameter("ids", heartId)
                .executeUpdate();
        em.clear();
        return cnt;
    }

    @Override
    public Optional<ChargerHeart> findByIds(Member member, Charger charger) {
        List<ChargerHeart> chargerHearts = em.createQuery("select ch from ChargerHeart ch " +
                        "where ch.member=:members and ch.charger=:chargers")
                .setParameter("members", member)
                .setParameter("chargers", charger)
                .getResultList();

        return chargerHearts.stream().findAny();
    }

    @Override
    public Optional<ChargerHeart> findByIdsIfLikes(Member member, Charger charger) {
        List <ChargerHeart> chargerHearts = em.createQuery("select ch from ChargerHeart ch " +
                        "where ch.member=:members and ch.charger=:chargers")
                .setParameter("members", member)
                .setParameter("chargers", charger)
                .getResultList();

        return chargerHearts.stream().findAny();
    }
}
