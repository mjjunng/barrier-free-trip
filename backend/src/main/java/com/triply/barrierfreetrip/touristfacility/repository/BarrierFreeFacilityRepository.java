package com.triply.barrierfreetrip.touristfacility.repository;

import com.triply.barrierfreetrip.touristfacility.domain.BarrierFreeFacility;

import java.util.List;

public interface BarrierFreeFacilityRepository {
    public BarrierFreeFacility findByContentId(String contentId);
}
