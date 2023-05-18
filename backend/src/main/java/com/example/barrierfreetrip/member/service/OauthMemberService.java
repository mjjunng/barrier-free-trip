package com.example.barrierfreetrip.member.service;

import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.dto.MemberResponseDto;
import com.example.barrierfreetrip.member.dto.SocialMemberDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletResponse;

public interface OauthMemberService {
    public MemberResponseDto oauthLogin(String code, String type) throws JsonProcessingException;
    public String getAccessToken(String code) throws JsonProcessingException;
    public SocialMemberDto getOauthMemberInfo(String accessToken) throws JsonProcessingException;
    public Member registerOauthMemberIfNeed(SocialMemberDto kakaoMemberDto);

}
