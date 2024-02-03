package com.triply.barrierfreetrip.touristfacility.service;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.touristfacility.domain.BarrierFreeFacility;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacilityHeart;
import com.triply.barrierfreetrip.touristfacility.dto.TouristFacilityInfoResponseDto;
import com.triply.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;
import com.triply.barrierfreetrip.touristfacility.repository.TouristFacilityHeartRepository;
import com.triply.barrierfreetrip.touristfacility.repository.TouristFacilityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TouristFacilityServiceImpl implements TouristFacilityService {
    private final TouristFacilityRepository touristFacilityRepository;
    private final TouristFacilityHeartRepository touristFacilityHeartRepository;
    private final BarrierFreeFacilityService barrierFreeFacilityService;

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
    public List<TouristFacilityListResponseDto> returnListDto(Member member, String contentTypeId,
                                                              String areaCode, String sigunguCode) {
        List<TouristFacility> touristFacilities = findByCode(contentTypeId, areaCode, sigunguCode);
        List<TouristFacilityListResponseDto> result = new ArrayList<>();

        for (TouristFacility tf: touristFacilities) {
            TouristFacilityListResponseDto  dto = new TouristFacilityListResponseDto(tf.getContentId(), tf.getContentTypeId(),
                    tf.getTitle(), tf.getAddr1(), tf.getRating(), tf.getFirstimage(), tf.getTel());

            Optional<TouristFacilityHeart> heart = touristFacilityHeartRepository.findByIdsIfLikes(member, tf);
            dto.setLike(heart.isPresent());
            result.add(dto);
        }

        return result;
    }
    @Override
    public TouristFacilityInfoResponseDto returnInfoDto(Member member, String contentId) {
        List<String> imgs = findImgByContentId(contentId);
        TouristFacility facility = findByContentId(contentId);
        BarrierFreeFacility barrierFreeFacility = barrierFreeFacilityService.findByContentId(contentId);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TouristFacilityInfoResponseDto dto = new TouristFacilityInfoResponseDto();

        if (barrierFreeFacility != null) {
             dto = modelMapper.map(barrierFreeFacility,
                    TouristFacilityInfoResponseDto.class);
        }

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

        Optional<TouristFacilityHeart> heart = touristFacilityHeartRepository.findByIdsIfLikes(member, facility);

        if (heart.isPresent()) {
            dto.setLike(1);
        } else {
            dto.setLike(0);
        }
        return dto;
    }

    @Override
    public List<TouristFacilityListResponseDto> returnNearHotelDto(Double userX, Double userY, int dis) {
        List<TouristFacility> nearHotels = touristFacilityRepository.findNearHotelsByPos(userX, userY, dis);
        return nearHotels.stream()
                .map(tf -> new TouristFacilityListResponseDto(tf.getContentId(), tf.getContentId(),
                        tf.getTitle(), tf.getAddr1(), tf.getRating(), tf.getFirstimage(), tf.getTel()))
                .collect(Collectors.toList());

    }

}
