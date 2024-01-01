package com.triply.barrierfreetrip.heart.service;

import com.triply.barrierfreetrip.member.domain.Member;

public interface HeartService {
    public void likes(Member member, String contentId, int likes);
}
