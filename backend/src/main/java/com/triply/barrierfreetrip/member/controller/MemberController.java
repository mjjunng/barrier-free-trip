package com.triply.barrierfreetrip.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.member.domain.Token;
import com.triply.barrierfreetrip.member.dto.MemberResponseDto;
import com.triply.barrierfreetrip.member.service.OauthMemberService;
import com.triply.barrierfreetrip.member.service.RefreshTokenService;
import com.triply.barrierfreetrip.member.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final OauthMemberService oauthMemberService;
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;

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

        // save refresh token
        refreshTokenService.saveRefreshToken(token);
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

        // save refresh token
        refreshTokenService.saveRefreshToken(token);
        MemberResponseDto memberResponseDto = new MemberResponseDto(member.getEmail(), member.getNickname(),
                                                                    token.getAccessToken(), token.getRefreshToken());
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseDto);
    }

    @PostMapping("/mylogout")
    public ResponseEntity logout(@RequestBody HashMap<String, String> bodyJson) {
        String refreshToken = bodyJson.get("refreshToken");
        Map<String, String> result = new HashMap<>();

        try {
            // delete refresh token
            refreshTokenService.deleteRefreshToken(refreshToken);
            result.put("message", "Success Logout");

        } catch (Exception e) {
            result.put("message", "Fail Logout: " + e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
