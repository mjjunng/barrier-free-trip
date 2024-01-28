package com.triply.barrierfreetrip.heart.controller;

import com.triply.barrierfreetrip.caretrip.service.CareTripService;
import com.triply.barrierfreetrip.charger.service.ChargerService;
import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.rental.service.RentalService;
import com.triply.barrierfreetrip.touristfacility.service.TouristFacilityHeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final TouristFacilityHeartService touristFacilityHeartService;
    private final CareTripService careTripService;
    private final ChargerService chargerService;
    private final RentalService rentalService;

    @GetMapping("/heart/{type}/{contentId}/{likes}")
    public void heart(@PathVariable("type") int type,
                      @PathVariable("contentId") String contentId,
                      @PathVariable("likes") int likes) {

        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (type == 0) {    // 관광 시설
            touristFacilityHeartService.likes(member, contentId, likes);
        } else if (type == 1) {     // 충전기
            chargerService.likes(member, Long.parseLong(contentId), likes);

        } else if (type == 2){    // 돌봄 시설
            careTripService.likes(member, Long.parseLong(contentId), likes);
        } else {    // 렌탈
            rentalService.likes(member, Long.parseLong(contentId), likes);
        }

    }
}
