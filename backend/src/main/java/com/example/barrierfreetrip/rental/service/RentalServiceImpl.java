package com.example.barrierfreetrip.rental.service;

import com.example.barrierfreetrip.charger.domain.Charger;
import com.example.barrierfreetrip.charger.domain.ChargerHeart;
import com.example.barrierfreetrip.charger.dto.ChargerListDto;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.repository.MemberRepository;
import com.example.barrierfreetrip.member.service.OauthMemberService;
import com.example.barrierfreetrip.rental.domain.Rental;
import com.example.barrierfreetrip.rental.domain.RentalHeart;
import com.example.barrierfreetrip.rental.dto.RentalListDto;
import com.example.barrierfreetrip.rental.repository.RentalHeartRepository;
import com.example.barrierfreetrip.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService{
    private final RentalRepository rentalRepository;
    private final OauthMemberService memberService;
    private final RentalHeartRepository rentalHeartRepository;

    @Override
    public List<RentalListDto> returnRentalServiceList(Member member, String sido, String sigungu) {
        List<Rental> rentals = rentalRepository.findByAreaName(sido, sigungu);
        List<RentalListDto> result = new ArrayList<>();

        for (Rental r: rentals) {
            RentalListDto dto = new RentalListDto(r.getTitle(), r.getAddr(), r.getTel());
            Optional<RentalHeart> likes = rentalHeartRepository.findByIdsIfLikes(member, r);
            if (likes.isPresent()) {
                dto.setLike(1);
            } else {
                dto.setLike(0);
            }
            result.add(dto);
        }

        return result;

    }

    public void likes(Member member, Long contentId, int likes) {
        Optional<Rental> rental = rentalRepository.findById(contentId);

        if (likes == 1) {  // 찜 추가
            if (rental.isPresent()) {
                RentalHeart rentalHeart = new RentalHeart(member, rental.get());
                rentalHeartRepository.save(rentalHeart);
            }

        } else {    // 찜 해제
            if (rental.isPresent()) {
                Optional<RentalHeart> rentalHeart = rentalHeartRepository.findByIds(member, rental.get());
                if (rentalHeart.isPresent()) {
                    int cnt = rentalHeartRepository.delete(rentalHeart.get().getId());
                }
            }

        }
    }
}
