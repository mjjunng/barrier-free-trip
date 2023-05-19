package com.example.barrierfreetrip.touristfacility.controller;

import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityResponseDto;
import com.example.barrierfreetrip.touristfacility.service.TouristFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TouristFacilityController {
    private TouristFacilityService touristFacilityService;

    @GetMapping("/tourist-facility-list/{contentTypeId}/{areaCode}/{sigunguCode}")
    public ResponseEntity returnTouristList(@RequestParam("contentTypeId") String contentTypeId,
                                            @RequestParam("areaCode") String areaCode,
                                            @RequestParam("sigunguCode") String sigunguCode
                                            ) {

        List<TouristFacilityResponseDto> touristFacility = touristFacilityService.
                findByCode(contentTypeId, areaCode, sigunguCode);

        return ResponseEntity.status(HttpStatus.OK).body(touristFacility);

    }
}
