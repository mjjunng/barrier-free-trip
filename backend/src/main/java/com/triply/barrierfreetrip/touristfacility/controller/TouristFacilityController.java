package com.triply.barrierfreetrip.touristfacility.controller;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.member.repository.MemberRepository;
import com.triply.barrierfreetrip.review.dto.ReviewListDto;
import com.triply.barrierfreetrip.touristfacility.domain.AreaCode;
import com.triply.barrierfreetrip.touristfacility.dto.SidoResponseDto;
import com.triply.barrierfreetrip.touristfacility.dto.SigunguResponseDto;
import com.triply.barrierfreetrip.touristfacility.dto.TouristFacilityInfoResponseDto;
import com.triply.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;
import com.triply.barrierfreetrip.touristfacility.repository.AreaCodeRepository;
import com.triply.barrierfreetrip.touristfacility.service.TouristFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TouristFacilityController {
    private final TouristFacilityService touristFacilityService;
    private final MemberRepository memberRepository;
    private final AreaCodeRepository areaCodeRepository;

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
        //Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberRepository.findById(Long.valueOf(41)).get();
        TouristFacilityInfoResponseDto result = touristFacilityService.returnInfoDto(member, contentId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/near-hotels/{userX}/{userY}")
    public ResponseEntity returnNearHotels(@PathVariable("userX") Double userX,
                                           @PathVariable("userY") Double userY) {
        List<TouristFacilityListResponseDto> result = touristFacilityService.returnNearHotelDto(userX, userY, 3);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/sido")
    public ResponseEntity returnSidoCode() {
        List<AreaCode> sidoCodes = areaCodeRepository.getSidoCode();
        List<SidoResponseDto> result = sidoCodes.stream().map(sc -> new SidoResponseDto(sc.getAreaCodeId().getAreaCd()
                                                                    , sc.getCdNm())).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/sido/{sidoCode}")
    public ResponseEntity returnSigunguCode(@PathVariable("sidoCode") String sidoCode) {
        List<AreaCode> sigunguCodes = areaCodeRepository.findByAreaCode(sidoCode);
        List<SigunguResponseDto> result = sigunguCodes.stream().map(sc -> new SigunguResponseDto(sc.getAreaCodeId().getSigunguCd()
                                                                    , sc.getCdNm())).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

}
