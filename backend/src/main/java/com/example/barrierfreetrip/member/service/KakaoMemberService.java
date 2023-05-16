package com.example.barrierfreetrip.member.service;

import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.dto.MemberResponseDto;
import com.example.barrierfreetrip.member.dto.SocialMemberDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;

public interface KakaoMemberService {
    public MemberResponseDto kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException;
    public String getAccessToken(String code) throws JsonProcessingException;
    public SocialMemberDto getKakaoMemberInfo(String accessToken) throws JsonProcessingException;
    public Member registerKakaoMemberIfNeed(SocialMemberDto kakaoMemberDto);

}
