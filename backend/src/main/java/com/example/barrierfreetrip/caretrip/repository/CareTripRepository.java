package com.example.barrierfreetrip.caretrip.repository;


import com.example.barrierfreetrip.caretrip.domain.CareTrip;
import com.example.barrierfreetrip.caretrip.domain.CareTripHeart;
import com.example.barrierfreetrip.heart.domain.Heart;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacility;

import java.util.List;
import java.util.Optional;

public interface CareTripRepository {
    public List<CareTrip> findAll();
    Optional<CareTripHeart> findByIdsIfLikes(Member member, CareTrip careTrip);
}
