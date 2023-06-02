package com.example.barrierfreetrip.caretrip.service;

import com.example.barrierfreetrip.caretrip.domain.CareTrip;
import com.example.barrierfreetrip.caretrip.domain.CareTripHeart;
import com.example.barrierfreetrip.caretrip.dto.CareTripListResponseDto;
import com.example.barrierfreetrip.caretrip.repository.CareTripRepository;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.service.OauthMemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CareTripServiceImpl implements CareTripService{
    private final CareTripRepository careTripRepository;
    private final OauthMemberService memberService;
    public List<CareTripListResponseDto> returnListDto(Long memberId) {
        List<CareTrip> careTrips = careTripRepository.findAll();
        List<CareTripListResponseDto> result = new ArrayList<>();
        Optional<Member> member = memberService.findById(memberId);

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        if (member.isPresent()) {
            for (CareTrip c: careTrips) {
                CareTripListResponseDto dto = modelMapper.map(c, CareTripListResponseDto.class);
                Optional<CareTripHeart> likes = careTripRepository.findByIdsIfLikes(member.get(), c);
                if (likes.isPresent()) {
                    dto.setLike(1);
                } else {
                    dto.setLike(0);
                }
                result.add(dto);
            }
        }
        return result;

    }
}
