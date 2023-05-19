package com.example.barrierfreetrip.touristfacility.service;

import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityResponseDto;
import com.example.barrierfreetrip.touristfacility.repository.TouristFacilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TouristFacilityServiceImpl implements TouristFacilityService {
    private TouristFacilityRepository touristFacilityRepository;
    @Override
    public List<TouristFacilityResponseDto> findByCode(String contentTypeId, String areaCode, String sigunguCode) {
        return touristFacilityRepository.findByCode(contentTypeId, areaCode, sigunguCode);
    }
}
