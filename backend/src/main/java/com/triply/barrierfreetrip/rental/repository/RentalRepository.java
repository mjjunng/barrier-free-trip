package com.triply.barrierfreetrip.rental.repository;

import com.triply.barrierfreetrip.rental.domain.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalRepository {
    public List<Rental> findByAreaName(String sido, String sigungu);
    public Optional<Rental> findById(Long id);
    public Optional<Rental> findByTitle(String keyword);
}
