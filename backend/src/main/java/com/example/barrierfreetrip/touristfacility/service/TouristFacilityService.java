package com.example.barrierfreetrip.touristfacility.service;

import com.example.barrierfreetrip.touristfacility.dto.BarrierFreeFacility;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacility;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityInfoResponseDto;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;

import java.util.List;

public interface TouristFacilityService {
    public List<TouristFacility> findByCode(String contentTypeId,
                                            String areaCode,
                                            String sigunguCode);

    public List<String> findImgByContentId(String contentId);

    public TouristFacility findByContentId(String contentId);

    List<TouristFacilityListResponseDto> returnListDto(String contentTypeId, String areaCode, String sigunguCode);
    public TouristFacilityInfoResponseDto returnInfoDto(Long memberId, String contentId);
}
