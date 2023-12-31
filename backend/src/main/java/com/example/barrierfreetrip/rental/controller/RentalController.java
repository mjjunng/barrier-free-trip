package com.example.barrierfreetrip.rental.controller;

import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.service.OauthMemberService;
import com.example.barrierfreetrip.rental.dto.RentalListDto;
import com.example.barrierfreetrip.rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RentalController {
    private final RentalService rentalService;
    private final OauthMemberService memberService;

    @GetMapping("/rentals/{sido}/{sigungu}")
    public ResponseEntity returnRentalServiceList(@PathVariable("sido") String sido,
                                                  @PathVariable("sigungu") String sigungu) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberService.findByEmail(email);
        List<RentalListDto> result = new ArrayList<>();

        if (member.isPresent()) {
            result = rentalService.returnRentalServiceList(member.get(), sido, sigungu);
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
