package com.example.barrierfreetrip.heart.service;

import com.example.barrierfreetrip.member.domain.Member;

public interface HeartService {
    public void likes(Member member, String contentId, int likes);
}
