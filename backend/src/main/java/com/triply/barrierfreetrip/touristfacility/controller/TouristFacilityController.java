package com.triply.barrierfreetrip.touristfacility.controller;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.touristfacility.dto.TouristFacilityInfoResponseDto;
import com.triply.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;
import com.triply.barrierfreetrip.touristfacility.service.TouristFacilityService;
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
public class TouristFacilityController {
    private final TouristFacilityService touristFacilityService;

    /**
     * 관광 시설 리스트 리턴 api
     * @param contentTypeId
     * @param areaCode
     * @param sigunguCode
     * @return
     */
    @GetMapping("/tourist-facilities/{contentTypeId}/{areaCode}/{sigunguCode}")
    public ResponseEntity returnTouristList(@PathVariable("contentTypeId") String contentTypeId,
                                            @PathVariable("areaCode") String areaCode,
                                            @PathVariable("sigunguCode") String sigunguCode
                                            ) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<TouristFacilityListResponseDto> result =
                touristFacilityService.returnListDto(member, contentTypeId, areaCode, sigunguCode);

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping("/tourist-facilities/{contentId}")
    public ResponseEntity returnTouristInfo(@PathVariable("contentId") String contentId) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TouristFacilityInfoResponseDto result = touristFacilityService.returnInfoDto(member, contentId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/near-hotels/{userX}/{userY}")
    public ResponseEntity returnNearHotels(@PathVariable("userX") Double userX,
                                           @PathVariable("userY") Double userY) {
        List<TouristFacilityListResponseDto> result = touristFacilityService.returnNearHotelDto(userX, userY, 3);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
