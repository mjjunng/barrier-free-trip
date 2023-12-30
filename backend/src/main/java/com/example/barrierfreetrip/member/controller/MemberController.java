package com.example.barrierfreetrip.member.controller;

import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.domain.Token;
import com.example.barrierfreetrip.member.dto.MemberResponseDto;
import com.example.barrierfreetrip.member.service.OauthMemberService;
import com.example.barrierfreetrip.member.service.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final OauthMemberService oauthMemberService;
    private final TokenService tokenService;

    @GetMapping("/welcome")
    public String healthCheck() {
        return "welcome";
    }


    @GetMapping("/oauth/kakao")
    public ResponseEntity kakaoLoin(@RequestParam("code") String code)
                                        throws JsonProcessingException {
        Member member = oauthMemberService.oauthLogin(code, "kakao");
        // generate jwt
        Token token = tokenService.generateToken(member.getEmail(), member.getRoles());

        MemberResponseDto memberResponseDto = new MemberResponseDto(member.getEmail(), member.getNickname(),
                                                                    token.getAccessToken(), token.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    @GetMapping("/oauth/naver")
    public ResponseEntity naverLoin(@RequestParam("code") String code)
            throws JsonProcessingException {
        Member member = oauthMemberService.oauthLogin(code, "naver");
        // generate jwt
        Token token = tokenService.generateToken(member.getEmail(), member.getRoles());

        MemberResponseDto memberResponseDto = new MemberResponseDto(member.getEmail(), member.getNickname(),
                                                                    token.getAccessToken(), token.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

}
