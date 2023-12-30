package com.example.barrierfreetrip.member.dto;

import com.example.barrierfreetrip.member.domain.Token;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {
    private String email;
    private String nickname;
    private String accessToken;
    private String refreshToken;
}
