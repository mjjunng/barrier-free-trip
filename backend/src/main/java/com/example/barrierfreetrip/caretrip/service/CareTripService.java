package com.example.barrierfreetrip.caretrip.service;


import com.example.barrierfreetrip.caretrip.dto.CareTripListResponseDto;

import java.util.List;

public interface CareTripService {
    public List<CareTripListResponseDto> returnListDto(Long memberId);
}
