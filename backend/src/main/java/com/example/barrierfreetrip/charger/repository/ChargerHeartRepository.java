package com.example.barrierfreetrip.charger.repository;

import com.example.barrierfreetrip.charger.domain.Charger;
import com.example.barrierfreetrip.charger.domain.ChargerHeart;
import com.example.barrierfreetrip.member.domain.Member;

import java.util.Optional;

public interface ChargerHeartRepository {
    public void save(ChargerHeart chargerHeart);
    public int delete(Long heartId);
    public Optional<ChargerHeart> findByIds(Member member, Charger charger);
    public Optional<ChargerHeart> findByIdsIfLikes(Member member, Charger charger);
}
