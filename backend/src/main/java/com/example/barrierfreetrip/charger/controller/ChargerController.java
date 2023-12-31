package com.example.barrierfreetrip.charger.controller;


import com.example.barrierfreetrip.charger.domain.Charger;
import com.example.barrierfreetrip.charger.dto.ChargerInfoDto;
import com.example.barrierfreetrip.charger.dto.ChargerListDto;
import com.example.barrierfreetrip.charger.service.ChargerService;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.service.OauthMemberService;
import com.example.barrierfreetrip.touristfacility.domain.TouristFacility;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
