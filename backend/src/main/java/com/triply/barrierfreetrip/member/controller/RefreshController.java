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
public class RefreshController {
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refresh")
    public ResponseEntity verifyRefreshToken(@RequestBody HashMap<String, String> bodyJson) {
        Map<String, String> map = refreshTokenService.verifyRefreshToken(bodyJson.get("refreshToken"));

        // if expired refreshtoken
        if(map.get("status").equals("402")){
            log.info("RefreshController - Refresh Token이 만료.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(map);
            //return new ResponseEntity<RefreshApiResponseMessage>(refreshApiResponseMessage, HttpStatus.UNAUTHORIZED);
        }

        // if effective refreshtoken
        log.info("RefreshController - Refresh Token이 유효.");
        return ResponseEntity.status(HttpStatus.OK).body(map);
        //return new ResponseEntity<RefreshApiResponseMessage>(refreshApiResponseMessage, HttpStatus.OK);
    }
}
