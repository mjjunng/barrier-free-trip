package com.example.barrierfreetrip.caretrip.service;


import com.example.barrierfreetrip.caretrip.dto.CareTripListResponseDto;
import com.example.barrierfreetrip.member.domain.Member;

import java.util.List;

public interface CareTripService {
    public List<CareTripListResponseDto> returnListDto(Member member, String sido, String sigungu);
    public void likes(Member member, Long contentId, int likes);
}
