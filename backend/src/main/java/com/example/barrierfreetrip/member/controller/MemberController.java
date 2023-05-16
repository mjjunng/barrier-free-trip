package com.example.barrierfreetrip.member.controller;

import com.example.barrierfreetrip.member.dto.MemberResponseDto;
import com.example.barrierfreetrip.member.service.KakaoMemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final KakaoMemberService kakaoMemberService;

    @GetMapping("/welcome")
    public String healthCheck() {
        return "welcome";
    }


    @GetMapping("/ouath/kakao")
    public ResponseEntity kakaoLoin(@RequestParam("code") String code, HttpServletResponse response)
                                        throws JsonProcessingException {

        MemberResponseDto memberResponseDto = kakaoMemberService.kakaoLogin(code, response);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }


}
