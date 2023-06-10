package com.example.barrierfreetrip.charger.service;

import com.example.barrierfreetrip.charger.dto.ChargerInfoDto;
import com.example.barrierfreetrip.charger.dto.ChargerListDto;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;

import java.util.List;

public interface ChargerService {
    public List<ChargerListDto> returnListDto(Long memberId, String areaCode);
    public void likes(Long memberId, Long contentId, int likes);

    public ChargerInfoDto returnChargerInfo(Long memberId, Long contentId);
    public List<ChargerListDto> returnNearChargerDto(Double userX, Double userY, int dis);
}
