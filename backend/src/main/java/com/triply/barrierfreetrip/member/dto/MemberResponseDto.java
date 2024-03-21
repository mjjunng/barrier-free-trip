package com.triply.barrierfreetrip.member.dto;

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
