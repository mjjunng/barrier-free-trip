package com.example.barrierfreetrip.touristfacility.service;

import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.touristfacility.domain.TouristFacility;
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
    public TouristFacilityInfoResponseDto returnInfoDto(Member member, String contentId);

    public List<TouristFacilityListResponseDto> returnNearHotelDto(Double userX, Double userY, int dis);
}
