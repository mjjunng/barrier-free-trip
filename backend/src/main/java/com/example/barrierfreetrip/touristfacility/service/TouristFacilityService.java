package com.example.barrierfreetrip.touristfacility.service;

import com.example.barrierfreetrip.touristfacility.dto.BarrierFreeFacility;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacility;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityInfoResponseDto;

import java.util.List;

public interface TouristFacilityService {
    public List<TouristFacility> findByCode(String contentTypeId,
                                            String areaCode,
                                            String sigunguCode);

    public List<String> findImgByContentId(String contentId);

    public TouristFacility findByContentId(String contentId);
    public TouristFacilityInfoResponseDto returnInfoDto(String contentId);
}
