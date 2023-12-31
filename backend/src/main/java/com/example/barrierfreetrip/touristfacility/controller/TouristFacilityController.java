package com.example.barrierfreetrip.touristfacility.controller;

import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.service.OauthMemberService;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityInfoResponseDto;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;
import com.example.barrierfreetrip.touristfacility.service.TouristFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class TouristFacilityController {
    private final TouristFacilityService touristFacilityService;
    private final OauthMemberService memberService;

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
        List<TouristFacilityListResponseDto> result =
                touristFacilityService.returnListDto(contentTypeId, areaCode, sigunguCode);

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping("/tourist-facilities/{contentId}")
    public ResponseEntity returnTouristInfo(@PathVariable("contentId") String contentId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberService.findByEmail(email);
        TouristFacilityInfoResponseDto result = new TouristFacilityInfoResponseDto();

        if (member.isPresent()) {
            result = touristFacilityService.returnInfoDto(member.get(), contentId);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/near-hotels/{userX}/{userY}")
    public ResponseEntity returnNearHotels(@PathVariable("userX") Double userX,
                                           @PathVariable("userY") Double userY) {
        List<TouristFacilityListResponseDto> result = touristFacilityService.returnNearHotelDto(userX, userY, 3);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
