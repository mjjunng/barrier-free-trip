package com.example.barrierfreetrip.touristfacility.repository;

import com.example.barrierfreetrip.touristfacility.dto.BarrierFreeFacility;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacility;

import java.util.List;
import java.util.Optional;

public interface TouristFacilityRepository {
    public List<TouristFacility> findByCode(String contentTypeId,
                                            String areaCode,
                                            String sigunguCode);
    public List<String> findImgByContentId(String contentId);

    public TouristFacility findByContentId(String contentId);
    public Optional<TouristFacility> findByTitle(String keyword);

}
