package com.example.barrierfreetrip.charger.service;

import com.example.barrierfreetrip.charger.dto.ChargerListDto;

import java.util.List;

public interface ChargerService {
    public List<ChargerListDto> returnListDto(Long memberId, String areaCode);
    public void likes(Long memberId, Long contentId, int likes);
}
