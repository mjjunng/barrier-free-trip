package com.example.barrierfreetrip.touristfacility.service;

import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityResponseDto;

import java.util.List;

public interface TouristFacilityService {
    public List<TouristFacilityResponseDto> findByCode(String contentTypeId,
                                                       String areaCode,
                                                       String sigunguCode);
}
