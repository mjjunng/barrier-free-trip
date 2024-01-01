package com.triply.barrierfreetrip.charger.controller;


import com.triply.barrierfreetrip.charger.dto.ChargerInfoDto;
import com.triply.barrierfreetrip.charger.dto.ChargerListDto;
import com.triply.barrierfreetrip.charger.service.ChargerService;
import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.member.service.OauthMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ChargerController {
    private final ChargerService chargerService;
    private final OauthMemberService memberService;

    @GetMapping("/chargers/{areaCode}")
    public ResponseEntity returnChargerList(@PathVariable("areaCode") String areaCode) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberService.findByEmail(email);
        List<ChargerListDto> result = new ArrayList<>();

        if (member.isPresent()) {
            result = chargerService.returnListDto(member.get(), areaCode);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping("/chargers/info/{contentId}")
    public ResponseEntity returnChargerInfo(@PathVariable("contentId") Long contentId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberService.findByEmail(email);
        ChargerInfoDto result = new ChargerInfoDto();

        if (member.isPresent()) {
            result = chargerService.returnChargerInfo(member.get(), contentId);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/near-chargers/{userX}/{userY}")
    public ResponseEntity returnNearChargers(@PathVariable("userX") double userX,
                                             @PathVariable("userY") double userY) {
        List<ChargerListDto> result = chargerService.returnNearChargerDto(userX, userY, 3);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
