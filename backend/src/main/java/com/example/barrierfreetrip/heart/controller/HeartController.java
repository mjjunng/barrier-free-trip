package com.example.barrierfreetrip.heart.controller;

import com.example.barrierfreetrip.caretrip.service.CareTripService;
import com.example.barrierfreetrip.charger.service.ChargerService;
import com.example.barrierfreetrip.heart.service.HeartService;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.service.OauthMemberService;
import com.example.barrierfreetrip.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;
    private final CareTripService careTripService;
    private final ChargerService chargerService;
    private final RentalService rentalService;
    private final OauthMemberService memberService;

    @GetMapping("/heart/{type}/{contentId}/{likes}")
    public void heart(@PathVariable("type") int type,
                      @PathVariable("contentId") String contentId,
                      @PathVariable("likes") int likes) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberService.findByEmail(email);

        if (member.isPresent()) {
            if (type == 0) {    // 관광 시설
                heartService.likes(member.get(), contentId, likes);
            } else if (type == 1) {     // 충전기
                chargerService.likes(member.get(), Long.parseLong(contentId), likes);

            } else if (type == 2){    // 돌봄 시설
                careTripService.likes(member.get(), Long.parseLong(contentId), likes);
            } else {    // 렌탈
                rentalService.likes(member.get(), Long.parseLong(contentId), likes);
            }
        }

    }
}
