package com.triply.barrierfreetrip.caretrip.repository;

import com.triply.barrierfreetrip.caretrip.domain.CareTrip;
import com.triply.barrierfreetrip.caretrip.domain.CareTripHeart;
import com.triply.barrierfreetrip.member.domain.Member;

import java.util.Optional;

public interface CareTripHeartRepository {
    public void save(CareTripHeart careTripHeart);
    public int delete(Long heartId);
    public Optional<CareTripHeart> findByIds(Member member, CareTrip careTrip);

    Optional<CareTripHeart> findByIdsIfLikes(Member member, CareTrip careTrip);
}
