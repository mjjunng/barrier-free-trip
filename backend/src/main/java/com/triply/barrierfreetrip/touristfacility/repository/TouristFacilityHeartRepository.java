package com.triply.barrierfreetrip.touristfacility.repository;

import com.triply.barrierfreetrip.touristfacility.domain.TouristFacilityHeart;
import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;

import java.util.Optional;

public interface TouristFacilityHeartRepository {
    TouristFacilityHeart findByIds(Member member, TouristFacility touristFacility);
    void save(TouristFacilityHeart touristFacilityHeart);

    int delete(Long hearId);

    Optional<TouristFacilityHeart> findByIdsIfLikes(Member member, TouristFacility facility);

}
