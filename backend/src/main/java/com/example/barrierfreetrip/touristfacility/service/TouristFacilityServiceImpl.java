package com.example.barrierfreetrip.touristfacility.service;

import com.example.barrierfreetrip.touristfacility.dto.BarrierFreeFacility;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacility;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityInfoResponseDto;
import com.example.barrierfreetrip.touristfacility.repository.TouristFacilityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TouristFacilityServiceImpl implements TouristFacilityService {
    private final TouristFacilityRepository touristFacilityRepository;
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


    public TouristFacilityInfoResponseDto returnInfoDto(String contentId) {
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

        return dto;
    }


}
