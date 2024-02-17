package com.triply.barrierfreetrip.charger.service;

import com.triply.barrierfreetrip.charger.domain.ChargerHeart;
import com.triply.barrierfreetrip.charger.dto.ChargerInfoDto;
import com.triply.barrierfreetrip.charger.dto.ChargerListDto;
import com.triply.barrierfreetrip.member.domain.Member;

import java.util.List;

public interface ChargerService {
    public List<ChargerListDto> returnListDto(Member member, String areaCode);
    public ChargerHeart likes(Member member, Long contentId, int likes);

    public ChargerInfoDto returnChargerInfo(Member member, Long contentId);
    public List<ChargerListDto> returnNearChargerDto(Double userX, Double userY, int dis);
}
