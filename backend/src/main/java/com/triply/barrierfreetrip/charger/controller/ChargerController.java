package com.triply.barrierfreetrip.charger.controller;


import com.triply.barrierfreetrip.charger.dto.ChargerInfoDto;
import com.triply.barrierfreetrip.charger.dto.ChargerListDto;
import com.triply.barrierfreetrip.charger.service.ChargerService;
import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChargerController {
    private final ChargerService chargerService;
    private final MemberRepository memberRepository;

    @GetMapping("/chargers/{sido}/{sigungu}")
    public ResponseEntity returnChargerList(@PathVariable("sido") String sido,
                                            @PathVariable("sigungu") String sigungu) {
        Member member = memberRepository.findById(Long.valueOf(41)).get();
        //Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ChargerListDto> result = chargerService.returnListDto(member, sido, sigungu);

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping("/chargers/info/{contentId}")
    public ResponseEntity returnChargerInfo(@PathVariable("contentId") Long contentId) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ChargerInfoDto result = chargerService.returnChargerInfo(member, contentId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/near-chargers/{userX}/{userY}")
    public ResponseEntity returnNearChargers(@PathVariable("userX") double userX,
                                             @PathVariable("userY") double userY) {
        List<ChargerListDto> result = chargerService.returnNearChargerDto(userX, userY, 3);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
