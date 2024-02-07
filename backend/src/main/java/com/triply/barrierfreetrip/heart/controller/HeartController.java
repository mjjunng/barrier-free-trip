package com.triply.barrierfreetrip.heart.controller;

import com.triply.barrierfreetrip.caretrip.domain.CareTrip;
import com.triply.barrierfreetrip.caretrip.domain.CareTripHeart;
import com.triply.barrierfreetrip.caretrip.dto.CareTripListResponseDto;
import com.triply.barrierfreetrip.caretrip.service.CareTripService;
import com.triply.barrierfreetrip.charger.domain.Charger;
import com.triply.barrierfreetrip.charger.domain.ChargerHeart;
import com.triply.barrierfreetrip.charger.dto.ChargerListDto;
import com.triply.barrierfreetrip.charger.service.ChargerService;
import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.member.service.OauthMemberService;
import com.triply.barrierfreetrip.rental.domain.Rental;
import com.triply.barrierfreetrip.rental.domain.RentalHeart;
import com.triply.barrierfreetrip.rental.dto.RentalListDto;
import com.triply.barrierfreetrip.rental.service.RentalService;
import com.triply.barrierfreetrip.review.dto.ReviewListDto;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacilityHeart;
import com.triply.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;
import com.triply.barrierfreetrip.touristfacility.service.TouristFacilityHeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class HeartController {

    private final TouristFacilityHeartService touristFacilityHeartService;
    private final CareTripService careTripService;
    private final ChargerService chargerService;
    private final RentalService rentalService;
    private final OauthMemberService memberService;

    @GetMapping("/heart/{type}/{contentId}/{likes}")
    @Transactional
    public void heart(@PathVariable("type") int type,
                      @PathVariable("contentId") String contentId,
                      @PathVariable("likes") int likes) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Member> member2 = memberService.findById(member.getId());
        if (member2.isPresent()) {
            if (type == 0) {    // 관광 시설
                TouristFacilityHeart heart = touristFacilityHeartService.likes(member, contentId, likes);
                if (heart != null) {
                    member2.get().getTouristFacilityHearts().add(heart);
                }

            } else if (type == 1) {     // 충전기
                ChargerHeart heart = chargerService.likes(member, Long.parseLong(contentId), likes);
                if (heart != null) {
                    member2.get().getChargerHearts().add(heart);
                }

            } else if (type == 2) {    // 돌봄 시설
                CareTripHeart heart = careTripService.likes(member, Long.parseLong(contentId), likes);
                if (heart != null) {
                    member2.get().getCareTripHearts().add(heart);
                }
            } else {    // 렌탈
                RentalHeart heart = rentalService.likes(member, Long.parseLong(contentId), likes);
                member2.get().getRentalHearts().add(heart);
            }
        }
    }

    @GetMapping("/heart/{type}")
    public ResponseEntity myHeartList(@PathVariable("type") int type) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Object> result = new ArrayList<>();
        Optional<Member> member2 = memberService.findById(member.getId());

        if (member2.isPresent()) {
            if (type == 1) {     // 충전기
                List<ChargerHeart> hearts = member2.get().getChargerHearts();
                for(ChargerHeart ch: hearts) {
                    Charger charger = ch.getCharger();
                    ChargerListDto chargerListDto = new ChargerListDto(charger.getId(), charger.getTitle(),
                                                                        charger.getAddr(), charger.getTel(), true);
                    result.add(chargerListDto);
                }
            } else if (type == 2) {    // 돌봄 시설
                List<CareTripHeart> hearts = member2.get().getCareTripHearts();
                for(CareTripHeart cth: hearts) {
                    CareTrip careTrip = cth.getCareTrip();
                    CareTripListResponseDto careTripListResponseDto = new CareTripListResponseDto(careTrip.getId(),
                                                careTrip.getTitle(), careTrip.getAddr(), careTrip.getTel(), true);
                    result.add(careTripListResponseDto);
                }

            } else if (type == 3) {    // 렌탈
                List<RentalHeart> hearts = member2.get().getRentalHearts();
                for(RentalHeart rh: hearts) {
                    Rental rental = rh.getRental();
                    RentalListDto rentalListDto = new RentalListDto(rental.getId(), rental.getTitle(),
                                                                    rental.getAddr(), rental.getTel(), true);
                    result.add(rentalListDto);
                }
            } else {
                List<TouristFacilityHeart> hearts = member2.get().getTouristFacilityHearts();
                for(TouristFacilityHeart h: hearts) {
                    TouristFacility tf = h.getTouristFacility();

                    if (tf.getContentTypeId().equals(Integer.toString(type))) {
                        TouristFacilityListResponseDto touristFacilityListResponseDto =
                                new TouristFacilityListResponseDto(tf.getContentId(), tf.getContentTypeId(),
                                        tf.getTitle(), tf.getAddr1(), tf.getRating(), tf.getFirstimage(), tf.getTitle(), true);
                        result.add(touristFacilityListResponseDto);
                    }
                }
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
