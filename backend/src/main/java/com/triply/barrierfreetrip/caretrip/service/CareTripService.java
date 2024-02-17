package com.triply.barrierfreetrip.caretrip.service;


import com.triply.barrierfreetrip.caretrip.domain.CareTripHeart;
import com.triply.barrierfreetrip.caretrip.dto.CareTripListResponseDto;
import com.triply.barrierfreetrip.member.domain.Member;

import java.util.List;

public interface CareTripService {
    public List<CareTripListResponseDto> returnListDto(Member member, String sido, String sigungu);
    public CareTripHeart likes(Member member, Long contentId, int likes);
}
