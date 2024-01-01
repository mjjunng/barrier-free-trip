package com.triply.barrierfreetrip.heart.repository;

import com.triply.barrierfreetrip.heart.domain.TouristHeart;
import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;

import java.util.Optional;

public interface HeartRepository {
    TouristHeart findByIds(Member member, TouristFacility touristFacility);
    void save(TouristHeart touristHeart);

    int delete(Long hearId);

    Optional<TouristHeart> findByIdsIfLikes(Member member, TouristFacility facility);

}
