package com.triply.barrierfreetrip.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRequestDto {
    private String serviceUserId;
    private String email;
    private String nickname;
}
