package com.example.barrierfreetrip.rental.service;

import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.rental.dto.RentalListDto;

import java.util.List;

public interface RentalService {
    public List<RentalListDto> returnRentalServiceList(Member member, String sido, String sigungu);
    public void likes(Member member, Long contentId, int likes);
}
