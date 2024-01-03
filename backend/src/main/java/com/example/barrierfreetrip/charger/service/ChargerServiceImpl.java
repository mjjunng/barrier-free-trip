package com.example.barrierfreetrip.charger.service;

import com.example.barrierfreetrip.caretrip.domain.CareTrip;
import com.example.barrierfreetrip.caretrip.domain.CareTripHeart;
import com.example.barrierfreetrip.caretrip.dto.CareTripListResponseDto;
import com.example.barrierfreetrip.charger.domain.Charger;
import com.example.barrierfreetrip.charger.domain.ChargerHeart;
import com.example.barrierfreetrip.charger.dto.ChargerInfoDto;
import com.example.barrierfreetrip.charger.dto.ChargerListDto;
import com.example.barrierfreetrip.charger.repository.ChargerHeartRepository;
import com.example.barrierfreetrip.charger.repository.ChargerRepository;
import com.example.barrierfreetrip.member.domain.Member;
import com.example.barrierfreetrip.member.service.OauthMemberService;
import com.example.barrierfreetrip.touristfacility.dto.TouristFacilityListResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChargerServiceImpl implements ChargerService{
    private final ChargerRepository chargerRepository;
    private final OauthMemberService memberService;
    private final ChargerHeartRepository chargerHeartRepository;


    public List<ChargerListDto> returnListDto(Long memberId, String areaCode) {
        List<Charger> chargers = chargerRepository.findByAreaCode(areaCode);
        Optional<Member> member = memberService.findById(memberId);
        List<ChargerListDto> result = new ArrayList<>();

        if (member.isPresent()) {
            for (Charger c: chargers) {
                ChargerListDto dto = new ChargerListDto(c.getId(), c.getTitle(), c.getAddr(), c.getTel());
                Optional<ChargerHeart> likes = chargerHeartRepository.findByIdsIfLikes(member.get(), c);
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

    public void likes(Long memberId, Long contentId, int likes) {
        Optional<Member> member = memberService.findById(memberId);
        Optional<Charger> charger = chargerRepository.findById(contentId);

        if (likes == 1) {  // 찜 추가
            if ((member.isPresent()) && (charger.isPresent())) {
                ChargerHeart chargerHeart = new ChargerHeart(member.get(), charger.get());
                chargerHeartRepository.save(chargerHeart);
            }

        } else {    // 찜 해제
            if ((member.isPresent()) && (charger.isPresent())) {
                Optional<ChargerHeart> chargerHeart = chargerHeartRepository.findByIds(member.get(), charger.get());
                if (chargerHeart.isPresent()) {
                    int cnt = chargerHeartRepository.delete(chargerHeart.get().getId());
                }
            }

        }
    }

    @Override
    public ChargerInfoDto returnChargerInfo(Long memberId, Long contentId) {
        Optional<Charger> charger = chargerRepository.findById(contentId);
        Optional<Member> member = memberService.findById(memberId);
        ChargerInfoDto result = new ChargerInfoDto();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        if ((member.isPresent()) && (charger.isPresent())) {
            result = modelMapper.map(charger.get(), ChargerInfoDto.class);
            Optional<ChargerHeart> likes = chargerHeartRepository.findByIdsIfLikes(member.get(), charger.get());

            if (likes.isPresent()) {
                result.setLike(1);
            } else {
                result.setLike(0);
            }

        }

        return result;
    }

    @Override
    public List<ChargerListDto> returnNearChargerDto(Double userX, Double userY, int dis) {
        List<Charger> nearChargers = chargerRepository.findNearChargersByPos(userX, userY, dis);
        return nearChargers.stream()
                .map(c -> new ChargerListDto(c.getId(), c.getTitle(), c.getAddr(), c.getTel()))
                .collect(Collectors.toList());
    }

}
