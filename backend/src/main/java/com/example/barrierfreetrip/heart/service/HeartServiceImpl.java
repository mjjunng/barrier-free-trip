package com.example.barrierfreetrip.heart.service;

import com.example.barrierfreetrip.heart.domain.TouristHeart;
import com.example.barrierfreetrip.heart.repository.HeartRepository;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.touristfacility.domain.TouristFacility;
import com.example.barrierfreetrip.touristfacility.service.TouristFacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class HeartServiceImpl implements HeartService{

    private final TouristFacilityService facilityService;
    private final HeartRepository heartRepository;

    public void likes(Member member, String contentId, int likes) {
        TouristFacility facility = facilityService.findByContentId(contentId);

        if (likes == 1) {  // 찜 추가
            TouristHeart touristHeart = new TouristHeart(member, facility);
            heartRepository.save(touristHeart);

        } else {    // 찜 해제
            TouristHeart prev = heartRepository.findByIds(member, facility);
            heartRepository.delete(prev.getId());
        }
    }
}
