package com.example.barrierfreetrip.rental.controller;

import com.example.barrierfreetrip.rental.dto.RentalListDto;
import com.example.barrierfreetrip.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    @GetMapping("/rentals/{memberId}/{sido}/{sigungu}")
    public ResponseEntity returnRentalServiceList(@PathVariable("memberId") Long memberId,
                                                  @PathVariable("sido") String sido,
                                                  @PathVariable("sigungu") String sigungu) {

        List<RentalListDto> result = rentalService.returnRentalServiceList(memberId, sido, sigungu);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
