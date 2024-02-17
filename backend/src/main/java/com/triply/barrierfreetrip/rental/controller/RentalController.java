package com.triply.barrierfreetrip.rental.controller;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.member.service.OauthMemberService;
import com.triply.barrierfreetrip.rental.dto.RentalListDto;
import com.triply.barrierfreetrip.rental.service.RentalService;
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
public class RentalController {
    private final RentalService rentalService;
    private final OauthMemberService memberService;

    @GetMapping("/rentals/{sido}/{sigungu}")
    public ResponseEntity returnRentalServiceList(@PathVariable("sido") String sido,
                                                  @PathVariable("sigungu") String sigungu) {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<RentalListDto> result = rentalService.returnRentalServiceList(member, sido, sigungu);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
