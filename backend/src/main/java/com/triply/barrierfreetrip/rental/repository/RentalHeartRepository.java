package com.triply.barrierfreetrip.rental.repository;

import com.triply.barrierfreetrip.member.domain.Member;
import com.triply.barrierfreetrip.rental.domain.Rental;
import com.triply.barrierfreetrip.rental.domain.RentalHeart;

import java.util.Optional;

public interface RentalHeartRepository {
    public void save(RentalHeart rentalHeart);
    public int delete(Long heartId);
    public Optional<RentalHeart> findByIds(Member member, Rental rental);
    public Optional<RentalHeart> findByIdsIfLikes(Member member, Rental rental);
}
