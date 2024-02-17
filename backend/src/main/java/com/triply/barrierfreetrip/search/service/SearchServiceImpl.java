package com.triply.barrierfreetrip.search.service;

import com.triply.barrierfreetrip.caretrip.domain.CareTrip;
import com.triply.barrierfreetrip.caretrip.repository.CareTripRepository;
import com.triply.barrierfreetrip.charger.domain.Charger;
import com.triply.barrierfreetrip.charger.repository.ChargerRepository;
import com.triply.barrierfreetrip.rental.domain.Rental;
import com.triply.barrierfreetrip.rental.repository.RentalRepository;
import com.triply.barrierfreetrip.search.SearchDto;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;
import com.triply.barrierfreetrip.touristfacility.repository.TouristFacilityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{
    private final TouristFacilityRepository touristFacilityRepository;
    private final CareTripRepository careTripRepository;
    private final ChargerRepository chargerRepository;
    private final RentalRepository rentalRepository;

    public SearchDto search(String keyword) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        SearchDto searchDto = new SearchDto();

        Optional<Rental> rental = rentalRepository.findByTitle(keyword);
        if (rental.isPresent()) {
            searchDto = modelMapper.map(rental.get(), SearchDto.class);
        } else {
            Optional<CareTrip> careTripe = careTripRepository.findByTitle(keyword);
            if (careTripe.isPresent()) {
                searchDto = modelMapper.map(careTripe.get(), SearchDto.class);
            } else {
                Optional<TouristFacility> tourist = touristFacilityRepository.findByTitle(keyword);
                if (tourist.isPresent()) {
                    searchDto = modelMapper.map(tourist.get(), SearchDto.class);
                    searchDto.setTitle(tourist.get().getTitle());
                    searchDto.setRating(tourist.get().getRating());
                    searchDto.setTel(tourist.get().getTel());
                    searchDto.setAddr(tourist.get().getAddr1());
                    searchDto.setFirstImage(tourist.get().getFirstimage());

                } else {
                    Optional<Charger> charger = chargerRepository.findByTitle(keyword);
                    if (charger.isPresent()) {
                        searchDto = modelMapper.map(charger.get(), SearchDto.class);
                    }
                }
            }
        }


        return searchDto;
    }

}
