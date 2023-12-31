package com.example.barrierfreetrip.caretrip.controller;

import com.example.barrierfreetrip.caretrip.domain.CareTrip;
import com.example.barrierfreetrip.caretrip.dto.CareTripListResponseDto;
import com.example.barrierfreetrip.caretrip.repository.CareTripRepository;
import com.example.barrierfreetrip.caretrip.service.CareTripService;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.service.OauthMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CareTripController {
    private final CareTripService careTripService;
    private final OauthMemberService memberService;

    /**
     * 돌봄 시설 리스트 리턴하는 함수
     * @return 돌봄 시설 리스트 리턴
     */
    @GetMapping("/care-services/{sido}/{sigungu}")
    public ResponseEntity returnCareServiceList(@PathVariable("sido") String sido,
                                                @PathVariable("sigungu") String sigungu){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberService.findByEmail(email);

        List<CareTripListResponseDto> result = new ArrayList<>();

        if (member.isPresent()) {
            result = careTripService.returnListDto(member.get(), sido, sigungu);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
