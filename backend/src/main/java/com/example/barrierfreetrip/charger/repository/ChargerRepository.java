package com.example.barrierfreetrip.charger.repository;

import com.example.barrierfreetrip.charger.domain.Charger;
import com.example.barrierfreetrip.touristfacility.domain.TouristFacility;

import java.util.List;
import java.util.Optional;

public interface ChargerRepository {
    public List<Charger> findByAreaCode(String areaCode);
    public Optional<Charger> findById(Long id);
    public Optional<Charger> findByTitle(String keyword);
    public List<Charger> findNearChargersByPos(Double userX, Double userY, double dis);
}
