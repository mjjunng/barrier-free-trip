package com.triply.barrierfreetrip.member.controller;

import com.triply.barrierfreetrip.member.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refresh")
    public ResponseEntity verifyRefreshToken(@RequestBody HashMap<String, String> bodyJson) {
        String newAccessToken = refreshTokenService.verifyRefreshToken(bodyJson.get("refreshToken"));
        Map<String, String> map = new HashMap<>();

        // if uneffective refreshtoken
        if(newAccessToken == null) {
            map.put("errortype", "Forbidden");
            map.put("status", "402");
            map.put("message", "Refresh 토큰이 유효하지 않습니다. 로그인이 필요합니다.");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
        }

        // if effective refreshtoken
        map.put("status", "200");
        map.put("message", "Refresh 토큰을 통한 Access Token 생성이 완료되었습니다.");
        map.put("accessToken", newAccessToken);

        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
}
