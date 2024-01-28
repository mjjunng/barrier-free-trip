package com.triply.barrierfreetrip.rental.service;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.rental.dto.RentalListDto;

import java.util.List;

public interface RentalService {
    public List<RentalListDto> returnRentalServiceList(Member member, String sido, String sigungu);
    public void likes(Member member, Long contentId, int likes);
}
