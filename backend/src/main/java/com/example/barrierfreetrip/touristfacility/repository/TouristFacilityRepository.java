package com.example.barrierfreetrip.touristfacility.repository;

import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityResponseDto;

import java.util.List;

public interface TouristFacilityRepository {
    public List<TouristFacilityResponseDto> findByCode(String contentTypeId,
                                                       String areaCode,
                                                       String sigunguCode);

}
