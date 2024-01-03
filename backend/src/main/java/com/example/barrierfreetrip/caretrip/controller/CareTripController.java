package com.example.barrierfreetrip.caretrip.controller;

import com.example.barrierfreetrip.caretrip.domain.CareTrip;
import com.example.barrierfreetrip.caretrip.dto.CareTripListResponseDto;
import com.example.barrierfreetrip.caretrip.repository.CareTripRepository;
import com.example.barrierfreetrip.caretrip.service.CareTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CareTripController {
    private final CareTripService careTripService;

    /**
     * 돌봄 시설 리스트 리턴하는 함수
     * @param memberId: 현재 사용자가 돌봄 시설 찜 했는지 확인하기 위해 필요
     * @return 돌봄 시설 리스트 리턴
     */
    @GetMapping("/care-service-list/{memberId}/{sido}/{sigungu}")
    public ResponseEntity returnCareServiceList(@PathVariable("memberId") Long memberId,
                                                @PathVariable("sido") String sido,
                                                @PathVariable("sigungu") String sigungu){
        List<CareTripListResponseDto> result = careTripService.returnListDto(memberId, sido, sigungu);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
