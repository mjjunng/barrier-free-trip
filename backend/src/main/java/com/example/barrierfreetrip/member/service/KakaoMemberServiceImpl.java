package com.example.barrierfreetrip.member.service;

import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.dto.MemberResponseDto;
import com.example.barrierfreetrip.member.dto.SocialMemberDto;
import com.example.barrierfreetrip.member.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class KakaoMemberServiceImpl implements KakaoMemberService{
    private final MemberRepository memberRepository;


    @Override
    public MemberResponseDto kakaoLogin(String code, HttpServletResponse response) throws JsonProcessingException {
        // 1. request access token from access code
        String accessToken = getAccessToken(code);

        // 2. call kakao api from acctess token -> get member info
        SocialMemberDto kakaoMemberInfo = getKakaoMemberInfo(accessToken);

        // 3. register to kakao id
        Member kakaoMember = registerKakaoMemberIfNeed(kakaoMemberInfo);
        MemberResponseDto memberResponseDto = new MemberResponseDto(kakaoMember.getId(), kakaoMember.getNickname());
        return memberResponseDto;

    }

    @Override
    public String getAccessToken(String code) throws JsonProcessingException {
        // create http header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // create http body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "f8ad3775d67d3a20c84521ba4d066769");
        body.add("redirect_uri", "http://localhost:8080/ouath/kakao");
        body.add("code", code);

        // send http request
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // http response (json) -> access token parsing
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        return jsonNode.get("access_token").asText();
    }

    @Override
    public SocialMemberDto getKakaoMemberInfo(String accessToken) throws JsonProcessingException {
        // create http header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // send http request
        HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoUserInfoRequest,
                String.class
        );

        // get member info from response body
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);

        String email = jsonNode.get("kakao_account").get("email").asText();
        String nickname = jsonNode.get("properties").get("nickname").asText();

        return new SocialMemberDto(email, nickname);
    }

    @Override
    public Member registerKakaoMemberIfNeed(SocialMemberDto kakaoMemberDto) {
        // check duplicate email
        String email = kakaoMemberDto.getEmail();
        String nickname = kakaoMemberDto.getNickname();

        Member kakoMember = memberRepository.findByEmail(email);

        if (kakoMember == null) {
            // register
            kakoMember = new Member(email, nickname);
            memberRepository.save(kakoMember);
        }

        return kakoMember;
    }
}
