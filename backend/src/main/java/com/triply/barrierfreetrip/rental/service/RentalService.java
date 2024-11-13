package com.triply.barrierfreetrip.rental.service;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.rental.domain.Rental;
import com.triply.barrierfreetrip.rental.domain.RentalHeart;
import com.triply.barrierfreetrip.rental.dto.RentalListDto;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    public List<RentalListDto> returnRentalServiceList(Member member, String sido, String sigungu);
    public RentalHeart likes(Member member, Long contentId, int likes);
    public Optional<Rental> findByTitle(String keyword);
}
