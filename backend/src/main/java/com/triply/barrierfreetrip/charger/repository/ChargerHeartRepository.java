package com.triply.barrierfreetrip.charger.repository;

import com.triply.barrierfreetrip.charger.domain.Charger;
import com.triply.barrierfreetrip.charger.domain.ChargerHeart;
import com.triply.barrierfreetrip.member.domain.Member;

import java.util.Optional;

public interface ChargerHeartRepository {
    public void save(ChargerHeart chargerHeart);
    public int delete(Long heartId);
    public Optional<ChargerHeart> findByIds(Member member, Charger charger);
    public Optional<ChargerHeart> findByIdsIfLikes(Member member, Charger charger);
}
