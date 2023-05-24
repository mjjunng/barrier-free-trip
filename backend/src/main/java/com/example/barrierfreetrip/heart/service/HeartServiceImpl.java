package com.example.barrierfreetrip.heart.service;

import com.example.barrierfreetrip.heart.domain.Heart;
import com.example.barrierfreetrip.heart.repository.HeartRepository;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.service.OauthMemberService;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacility;
import com.example.barrierfreetrip.touristfacility.service.TouristFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class HeartServiceImpl implements HeartService{

    private final OauthMemberService memberService;
    private final TouristFacilityService facilityService;
    private final HeartRepository heartRepository;

    public void likes(Long memberId, String contentId, int likes) {
        Optional<Member> member = memberService.findById(memberId);
        TouristFacility facility = facilityService.findByContentId(contentId);

        if (likes == 1) {  // 찜 추가
            if (member.isPresent()) {
                Heart heart = new Heart(member.get(), facility);
                heartRepository.save(heart);
            }

        } else {    // 찜 해제
            if (member.isPresent()) {
                Heart prev = heartRepository.findByIds(member.get(), facility);
                heartRepository.delete(prev.getId());
            }

        }
    }
}
