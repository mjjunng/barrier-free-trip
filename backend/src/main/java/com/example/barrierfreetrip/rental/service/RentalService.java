package com.example.barrierfreetrip.rental.service;

import com.example.barrierfreetrip.rental.dto.RentalListDto;

import java.util.List;

public interface RentalService {
    public List<RentalListDto> returnRentalServiceList(Long memberId, String sido, String sigungu);
    public void likes(Long memberId, Long contentId, int likes);
}
