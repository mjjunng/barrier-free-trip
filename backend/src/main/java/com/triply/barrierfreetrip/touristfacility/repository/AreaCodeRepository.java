package com.triply.barrierfreetrip.touristfacility.repository;

import com.triply.barrierfreetrip.touristfacility.domain.AreaCode;

import java.util.List;

public interface AreaCodeRepository {
    public List<AreaCode> getSidoCode();
    public List<AreaCode> findByAreaCode(String areaCode);
}
