package com.triply.barrierfreetrip.touristfacility.service;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;
import com.triply.barrierfreetrip.touristfacility.dto.TouristFacilityInfoResponseDto;
import com.triply.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;

import java.util.List;
import java.util.Optional;

public interface TouristFacilityService {
    public List<TouristFacility> findByCode(String contentTypeId,
                                            String areaCode,
                                            String sigunguCode);

    public List<String> findImgByContentId(String contentId);

    public TouristFacility findByContentId(String contentId);

    List<TouristFacilityListResponseDto> returnListDto(Member member, String contentTypeId, String areaCode, String sigunguCode);
    public TouristFacilityInfoResponseDto returnInfoDto(Member member, String contentId);

    public List<TouristFacilityListResponseDto> returnNearHotelDto(Double userX, Double userY, int dis);

    public void updateRating(TouristFacility touristFacility, double newRating);
    public Optional<TouristFacility> findByTitle(String keyword);
}
