package com.triply.barrierfreetrip.member.service;

import com.triply.barrierfreetrip.member.domain.RefreshToken;
import com.triply.barrierfreetrip.member.domain.Token;
import com.triply.barrierfreetrip.member.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private String secretKey = "testSecretKey202301125testSecretKey202301125testSecretKey202301125";
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

    public String verifyRefreshToken(String refreshToken) {
        if (getRefreshToken(refreshToken).isPresent()) {
            String savedRefreshToken = getRefreshToken(refreshToken).get().getRefreshToken();

            try {
                Jws<Claims> claims = Jwts.parser()
                        .setSigningKey(secretKey)
                        .parseClaimsJws(savedRefreshToken);

                // recreate access token
                if (!claims.getBody().getExpiration().before(new Date())) {
                    return recreationAccessToken(claims.getBody().get("sub").toString(), claims.getBody().get("roles"));
                }

            } catch (Exception e) {
                // if expired refresh token, need to login -> 예외 처리 필요!
                //System.out.println(e.getMessage());
                return null;
            }
        }
        // 저장된 refresh token이 없는 경우 -> 예외 처리 필요!
        return null;
    }

    public Optional<RefreshToken> getRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken);
    }

    public String recreationAccessToken(String email, Object roles) {
        long accessTokenPeriod = 1000L * 60L * 60L * 24L * 30L * 3L;
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", roles);

        Date now = new Date();

        String accessToken = Jwts.builder()
                .setClaims(claims)  // save info
                .setIssuedAt(now)   // token generated time info
                .setExpiration(new Date(now.getTime() + accessTokenPeriod)) // set expire time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // using encryption algorithm and set secret value
                .compact();

        return accessToken;
    }
}
