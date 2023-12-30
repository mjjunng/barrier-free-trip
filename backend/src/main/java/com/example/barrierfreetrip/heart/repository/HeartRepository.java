package com.example.barrierfreetrip.heart.repository;

import com.example.barrierfreetrip.heart.domain.TouristHeart;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.touristfacility.domain.TouristFacility;

import java.util.Optional;

public interface HeartRepository {
    TouristHeart findByIds(Member member, TouristFacility touristFacility);
    void save(TouristHeart touristHeart);

    int delete(Long hearId);

    Optional<TouristHeart> findByIdsIfLikes(Member member, TouristFacility facility);

}
