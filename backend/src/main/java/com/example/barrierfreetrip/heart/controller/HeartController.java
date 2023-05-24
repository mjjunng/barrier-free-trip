package com.example.barrierfreetrip.heart.controller;

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
    @GetMapping("/heart/{memberId}/{contentId}/{likes}")
    public void heart(@PathVariable("memberId") Long memberId,
                      @PathVariable("contentId") String contentId,
                      @PathVariable("likes") int likes) {
        heartService.likes(memberId, contentId, likes);

    }
}
