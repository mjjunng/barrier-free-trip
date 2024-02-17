package com.triply.barrierfreetrip.charger.service;

import com.triply.barrierfreetrip.charger.domain.Charger;
import com.triply.barrierfreetrip.charger.domain.ChargerHeart;
import com.triply.barrierfreetrip.charger.dto.ChargerInfoDto;
import com.triply.barrierfreetrip.charger.dto.ChargerListDto;
import com.triply.barrierfreetrip.charger.repository.ChargerHeartRepository;
import com.triply.barrierfreetrip.charger.repository.ChargerRepository;
import com.triply.barrierfreetrip.member.domain.Member;
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
    private final ChargerHeartRepository chargerHeartRepository;


    public List<ChargerListDto> returnListDto(Member member, String areaCode) {
        List<Charger> chargers = chargerRepository.findByAreaCode(areaCode);
        List<ChargerListDto> result = new ArrayList<>();

        for (Charger c: chargers) {
            ChargerListDto dto = new ChargerListDto(c.getId(), c.getTitle(), c.getAddr(), c.getTel());
            Optional<ChargerHeart> likes = chargerHeartRepository.findByIdsIfLikes(member, c);
            dto.setLike(likes.isPresent());
            result.add(dto);
        }

        return result;
    }

    public ChargerHeart likes(Member member, Long contentId, int likes) {
        Optional<Charger> charger = chargerRepository.findById(contentId);

        if (likes == 1) {  // 찜 추가
            if (charger.isPresent()) {
                ChargerHeart chargerHeart = new ChargerHeart(member, charger.get());
                chargerHeartRepository.save(chargerHeart);
                return chargerHeart;
            }

        } else {    // 찜 해제
            if (charger.isPresent()) {
                Optional<ChargerHeart> chargerHeart = chargerHeartRepository.findByIds(member, charger.get());
                if (chargerHeart.isPresent()) {
                    int cnt = chargerHeartRepository.delete(chargerHeart.get().getId());
                }
            }
        }
        return null;
    }

    @Override
    public ChargerInfoDto returnChargerInfo(Member member, Long contentId) {
        Optional<Charger> charger = chargerRepository.findById(contentId);
        ChargerInfoDto result = new ChargerInfoDto();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        if (charger.isPresent()) {
            result = modelMapper.map(charger.get(), ChargerInfoDto.class);
            Optional<ChargerHeart> likes = chargerHeartRepository.findByIdsIfLikes(member, charger.get());

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
