package com.triply.barrierfreetrip.touristfacility.service;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacilityHeart;

public interface TouristFacilityHeartService {
    public TouristFacilityHeart likes(Member member, String contentId, int likes);
}
