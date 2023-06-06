package com.example.barrierfreetrip.rental.repository;

import com.example.barrierfreetrip.rental.domain.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalRepository {
    public List<Rental> findByAreaName(String sido, String sigungu);
    public Optional<Rental> findById(Long id);
}
