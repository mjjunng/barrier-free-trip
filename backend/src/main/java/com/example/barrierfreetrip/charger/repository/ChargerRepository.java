package com.example.barrierfreetrip.charger.repository;

import com.example.barrierfreetrip.charger.domain.Charger;

import java.util.List;
import java.util.Optional;

public interface ChargerRepository {
    public List<Charger> findByAreaCode(String areaCode);
    public Optional<Charger> findById(Long id);
}
