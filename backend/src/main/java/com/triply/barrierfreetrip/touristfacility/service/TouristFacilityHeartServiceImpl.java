package com.triply.barrierfreetrip.touristfacility.service;

import com.triply.barrierfreetrip.touristfacility.domain.TouristFacilityHeart;
import com.triply.barrierfreetrip.touristfacility.repository.TouristFacilityHeartRepository;
import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
public class TouristFacilityHeartServiceImpl implements TouristFacilityHeartService {

    private final TouristFacilityService facilityService;
    private final TouristFacilityHeartRepository touristFacilityHeartRepository;

    public TouristFacilityHeart likes(Member member, String contentId, int likes) {
        TouristFacility facility = facilityService.findByContentId(contentId);

        if (likes == 1) {  // 찜 추가
            TouristFacilityHeart touristFacilityHeart = new TouristFacilityHeart(member, facility);
            touristFacilityHeartRepository.save(touristFacilityHeart);
            return touristFacilityHeart;

        } else {    // 찜 해제
            TouristFacilityHeart prev = touristFacilityHeartRepository.findByIds(member, facility);
            touristFacilityHeartRepository.delete(prev.getId());
        }
        return null;
    }
}
