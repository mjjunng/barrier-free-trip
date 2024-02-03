package com.triply.barrierfreetrip.caretrip.service;

import com.triply.barrierfreetrip.caretrip.domain.CareTrip;
import com.triply.barrierfreetrip.caretrip.domain.CareTripHeart;
import com.triply.barrierfreetrip.caretrip.dto.CareTripListResponseDto;
import com.triply.barrierfreetrip.caretrip.repository.CareTripHeartRepository;
import com.triply.barrierfreetrip.caretrip.repository.CareTripRepository;
import com.triply.barrierfreetrip.member.domain.Member;
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
    private final CareTripHeartRepository careTripHeartRepository;

    public List<CareTripListResponseDto> returnListDto(Member member, String sido, String sigungu) {
        List<CareTrip> careTrips = careTripRepository.findByAreaName(sido, sigungu);
        List<CareTripListResponseDto> result = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        for (CareTrip c: careTrips) {
            CareTripListResponseDto dto = modelMapper.map(c, CareTripListResponseDto.class);
            Optional<CareTripHeart> likes = careTripHeartRepository.findByIdsIfLikes(member, c);
            dto.setLike(likes.isPresent());
            result.add(dto);
        }

        return result;

    }

    public void likes(Member member, Long contentId, int likes) {
        Optional<CareTrip> careTrip = careTripRepository.findById(contentId);

        if (likes == 1) {  // 찜 추가
            if (careTrip.isPresent()) {
                CareTripHeart careTripHeart = new CareTripHeart(member, careTrip.get());
                careTripHeartRepository.save(careTripHeart);
            }

        } else {    // 찜 해제
            if (careTrip.isPresent()) {
                Optional<CareTripHeart> careTripHeart = careTripHeartRepository.findByIds(member, careTrip.get());
                if (careTripHeart.isPresent()) {
                    int cnt = careTripHeartRepository.delete(careTripHeart.get().getId());
                }
            }

        }
    }
}
