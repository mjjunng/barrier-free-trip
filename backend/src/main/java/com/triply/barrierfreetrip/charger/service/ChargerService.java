package com.triply.barrierfreetrip.charger.service;

import com.triply.barrierfreetrip.charger.domain.Charger;
import com.triply.barrierfreetrip.charger.domain.ChargerHeart;
import com.triply.barrierfreetrip.charger.dto.ChargerInfoDto;
import com.triply.barrierfreetrip.charger.dto.ChargerListDto;
import com.triply.barrierfreetrip.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface ChargerService {
    public List<ChargerListDto> returnListDto(Member member, String sido, String sigungu);
    public ChargerHeart likes(Member member, Long contentId, int likes);

    public ChargerInfoDto returnChargerInfo(Member member, Long contentId);
    public List<ChargerListDto> returnNearChargerDto(Double userX, Double userY, int dis);
    public Optional<Charger> findByTitle(String keyword);
}
