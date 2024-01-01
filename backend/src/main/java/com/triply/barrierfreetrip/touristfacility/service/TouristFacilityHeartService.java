package com.triply.barrierfreetrip.touristfacility.service;

import com.triply.barrierfreetrip.member.domain.Member;

public interface TouristFacilityHeartService {
    public void likes(Member member, String contentId, int likes);
}
