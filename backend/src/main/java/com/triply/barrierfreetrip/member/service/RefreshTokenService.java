package com.triply.barrierfreetrip.member.service;

import com.triply.barrierfreetrip.member.domain.RefreshToken;
import com.triply.barrierfreetrip.member.domain.Token;
import com.triply.barrierfreetrip.member.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;

    public void saveRefreshToken(Token token) {
        String loginMemberEmail = token.getKeyEmail();
        RefreshToken refreshToken = RefreshToken.builder()
                                    .keyEmail(loginMemberEmail)
                                    .refreshToken(token.getRefreshToken()).build();

        // if refresh token exists, delete refresh token
        if (refreshTokenRepository.existsByKeyEmail(loginMemberEmail)) {
            refreshTokenRepository.deleteByKeyEmail(loginMemberEmail);
        }

        // save refresh token
        refreshTokenRepository.save(refreshToken);
    }

    public Map<String, String> verifyRefreshToken(String refreshToken) {
        if (getRefreshToken(refreshToken).isPresent()) {
            RefreshToken refreshToken1 = getRefreshToken(refreshToken).get();
            String newAccessToken = tokenService.verifyRefreshToken(refreshToken1);

            return createRefreshJson(newAccessToken);
        }

        Map<String, String> map = new HashMap<>();

        map.put("errortype", "Forbidden");
        map.put("status", "402");
        map.put("message", "저장된 Refresh 토큰이 없습니다.");

        return map;
    }

    public Optional<RefreshToken> getRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken);
    }

    public Map<String, String> createRefreshJson(String newAccessToken) {
        Map<String, String> map = new HashMap<>();

        if (newAccessToken == null) {
            map.put("errortype", "Forbidden");
            map.put("status", "402");
            map.put("message", "Refresh 토큰이 만료되었습니다. 로그인이 필요합니다.");

            return map;
        }

        map.put("status", "200");
        map.put("message", "Refresh 토큰을 통한 Access Token 생성이 완료되었습니다.");
        map.put("accessToken", newAccessToken);

        return map;
    }

}
