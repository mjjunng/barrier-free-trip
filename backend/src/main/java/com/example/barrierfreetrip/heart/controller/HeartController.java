package com.example.barrierfreetrip.heart.controller;

import com.example.barrierfreetrip.caretrip.service.CareTripService;
import com.example.barrierfreetrip.charger.service.ChargerService;
import com.example.barrierfreetrip.heart.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;
    private final CareTripService careTripService;
    private final ChargerService chargerService;

    @GetMapping("/heart/{memberId}/{type}/{contentId}/{likes}")
    public void heart(@PathVariable("memberId") Long memberId,
                      @PathVariable("type") int type,
                      @PathVariable("contentId") String contentId,
                      @PathVariable("likes") int likes) {

        if (type == 0) {    // 관광 시설
            heartService.likes(memberId, contentId, likes);
        } else if (type == 1) {     // 충전기
            chargerService.likes(memberId, Long.parseLong(contentId), likes);

        } else {    // 돌봄 시설
            careTripService.likes(memberId, Long.parseLong(contentId), likes);
        }


    }
}
