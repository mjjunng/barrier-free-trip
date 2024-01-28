package com.triply.barrierfreetrip.member.service;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.member.dto.SocialMemberDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface OauthMemberService {
    public Member oauthLogin(String code, String type) throws JsonProcessingException;
    public String getAccessToken(String code) throws JsonProcessingException;
    public SocialMemberDto getOauthMemberInfo(String accessToken) throws JsonProcessingException;
    public Member registerOauthMemberIfNeed(SocialMemberDto kakaoMemberDto);

    Optional<Member> findById(Long memberId);
    public UserDetails loadUserByUsername(String username);

    Optional<Member> findByEmail(String email);
}
