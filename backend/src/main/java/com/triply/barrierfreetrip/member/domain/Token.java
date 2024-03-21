package com.triply.barrierfreetrip.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class Token{
    private String accessToken;
    private String refreshToken;
    private String keyEmail;
}
