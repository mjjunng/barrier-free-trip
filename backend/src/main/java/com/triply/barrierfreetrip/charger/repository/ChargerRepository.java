package com.triply.barrierfreetrip.charger.repository;

import com.triply.barrierfreetrip.charger.domain.Charger;

import java.util.List;
import java.util.Optional;

public interface ChargerRepository {
    public List<Charger> findByAreaCode(String sido, String sigungu);
    public Optional<Charger> findById(Long id);
    public Optional<Charger> findByTitle(String keyword);
    public List<Charger> findNearChargersByPos(Double userX, Double userY, double dis);
}
