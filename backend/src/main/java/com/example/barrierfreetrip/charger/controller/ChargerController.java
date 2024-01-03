package com.example.barrierfreetrip.charger.controller;


import com.example.barrierfreetrip.charger.domain.Charger;
import com.example.barrierfreetrip.charger.dto.ChargerInfoDto;
import com.example.barrierfreetrip.charger.dto.ChargerListDto;
import com.example.barrierfreetrip.charger.service.ChargerService;
import com.example.barrierfreetrip.touristfacility.domain.TouristFacility;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ChargerController {
    private final ChargerService chargerService;

    @GetMapping("/chargers/{memberId}/{areaCode}")
    public ResponseEntity returnChargerList(@PathVariable("memberId") Long memberId,
                                            @PathVariable("areaCode") String areaCode) {

        List<ChargerListDto> result = chargerService.returnListDto(memberId, areaCode);

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping("/chargers/info/{memberId}/{contentId}")
    public ResponseEntity returnChargerInfo(@PathVariable("memberId") Long memberId,
                                            @PathVariable("contentId") Long contentId) {
        ChargerInfoDto result = chargerService.returnChargerInfo(memberId, contentId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/near-chargers/{userX}/{userY}")
    public ResponseEntity returnNearChargers(@PathVariable("userX") double userX,
                                             @PathVariable("userY") double userY) {
        List<ChargerListDto> result = chargerService.returnNearChargerDto(userX, userY, 3);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
