package com.example.barrierfreetrip.touristfacility.service;

import com.example.barrierfreetrip.heart.domain.Heart;
import com.example.barrierfreetrip.heart.repository.HeartRepository;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.repository.MemberRepository;
import com.example.barrierfreetrip.touristfacility.domain.BarrierFreeFacility;
import com.example.barrierfreetrip.touristfacility.domain.TouristFacility;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityInfoResponseDto;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;
import com.example.barrierfreetrip.touristfacility.repository.TouristFacilityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TouristFacilityServiceImpl implements TouristFacilityService {
    private final TouristFacilityRepository touristFacilityRepository;
    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<TouristFacility> findByCode(String contentTypeId, String areaCode, String sigunguCode) {
        return touristFacilityRepository.findByCode(contentTypeId, areaCode, sigunguCode);
    }

    @Override
    public List<String> findImgByContentId(String contentId) {
        return touristFacilityRepository.findImgByContentId(contentId);
    }

    @Override
    public TouristFacility findByContentId(String contentId) {
        return touristFacilityRepository.findByContentId(contentId);

    }
    @Override
    public List<TouristFacilityListResponseDto> returnListDto(String contentTypeId, String areaCode, String sigunguCode) {
        List<TouristFacility> touristFacilities = findByCode(contentTypeId, areaCode, sigunguCode);

        return touristFacilities.stream()
                .map(tf -> new TouristFacilityListResponseDto(tf.getContentId(), tf.getContentTypeId(),
                        tf.getTitle(), tf.getAddr1(), tf.getRating(), tf.getFirstimage()))
                .collect(Collectors.toList());
    }
    @Override
    public TouristFacilityInfoResponseDto returnInfoDto(Long memberId, String contentId) {
        List<String> imgs = findImgByContentId(contentId);
        TouristFacility facility = findByContentId(contentId);
        BarrierFreeFacility barrierFreeFacility = facility.getBarrierFreeFacility();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TouristFacilityInfoResponseDto dto = new TouristFacilityInfoResponseDto();

        if (barrierFreeFacility != null) {
             dto = modelMapper.map(barrierFreeFacility,
                    TouristFacilityInfoResponseDto.class);
        }

        Optional<Member> member = memberRepository.findById(memberId);

        dto.setImgs(imgs);
        dto.setContentId(facility.getContentId());
        dto.setContentTypeId(facility.getContentTypeId());
        dto.setTitle(facility.getTitle());
        dto.setAddr1(facility.getAddr1());
        dto.setAddr2(facility.getAddr2());
        dto.setOverview(facility.getOverview());
        dto.setHomepage(facility.getHomepage());
        dto.setTel(facility.getTel());
        dto.setCheckInTime(facility.getCheckInTime());
        dto.setCheckOutTime(facility.getCheckOutTime());
        dto.setParking(facility.getParking());
        dto.setRating(facility.getRating());
        dto.setAreaCode(facility.getAreaCode());
        dto.setSignguide(facility.getSigunguCode());
        dto.setMapx(facility.getMapx());
        dto.setMapy(facility.getMapy());


        if (member.isPresent()) {
            Optional<Heart> heart = heartRepository.findByIdsIfLikes(member.get(), facility);

            if (heart.isPresent()) {
                dto.setLike(1);
            } else {
                dto.setLike(0);
            }
        }


        return dto;
    }

    @Override
    public List<TouristFacilityListResponseDto> returnNearHotelDto(Double userX, Double userY, int dis) {
        List<TouristFacility> nearHotels = touristFacilityRepository.findNearHotelsByPos(userX, userY, dis);
        return nearHotels.stream()
                .map(tf -> new TouristFacilityListResponseDto(tf.getContentId(), tf.getContentId(),
                        tf.getTitle(), tf.getAddr1(), tf.getRating(), tf.getFirstimage()))
                .collect(Collectors.toList());

    }

//    private double getDistance(double lat1, double lon1, double lat2, double lon2) {
//        int EARTH_RADIUS = 1;
//        double dLat = Math.toRadians(lat2 - lat1);
//        double dLon = Math.toRadians(lon2 - lon1);
//
//        double a = Math.sin(dLat/2)* Math.sin(dLat/2)+ Math.cos(Math.toRadians(lat1))* Math.cos(Math.toRadians(lat2))* Math.sin(dLon/2)* Math.sin(dLon/2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//        double distance = EARTH_RADIUS* c * 1000;    // Distance in m
//        return distance;
//    }


}
