package com.example.barrierfreetrip.touristfacility.repository;

import com.example.barrierfreetrip.touristfacility.dto.BarrierFreeFacility;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacility;

import java.util.List;

public interface TouristFacilityRepository {
    public List<TouristFacility> findByCode(String contentTypeId,
                                            String areaCode,
                                            String sigunguCode);
    public List<String> findImgByContentId(String contentId);

    public TouristFacility findByContentId(String contentId);

}
