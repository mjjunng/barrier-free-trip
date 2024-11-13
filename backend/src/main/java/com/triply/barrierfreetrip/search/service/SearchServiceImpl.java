package com.triply.barrierfreetrip.search.service;

import com.triply.barrierfreetrip.caretrip.domain.CareTrip;
import com.triply.barrierfreetrip.caretrip.service.CareTripService;
import com.triply.barrierfreetrip.charger.domain.Charger;
import com.triply.barrierfreetrip.charger.service.ChargerService;
import com.triply.barrierfreetrip.rental.domain.Rental;
import com.triply.barrierfreetrip.rental.service.RentalService;
import com.triply.barrierfreetrip.search.dto.SearchDto;
import com.triply.barrierfreetrip.touristfacility.domain.TouristFacility;
import com.triply.barrierfreetrip.touristfacility.service.TouristFacilityService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService{
    private final TouristFacilityService touristFacilityService;
    private final CareTripService careTripService;
    private final ChargerService chargerService;
    private final RentalService rentalService;

    public List<SearchDto> search(String keyword) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<SearchDto> results = new ArrayList<>();

        Optional<TouristFacility> tourist = touristFacilityService.findByTitle(keyword);
        Optional<CareTrip> careTripe = careTripService.findByTitle(keyword);
        Optional<Charger> charger = chargerService.findByTitle(keyword);
        Optional<Rental> rental = rentalService.findByTitle(keyword);

        if (tourist.isPresent()) {
            SearchDto searchDto = new SearchDto();
            searchDto.setTitle(tourist.get().getTitle());
            searchDto.setRating(tourist.get().getRating());
            searchDto.setTel(tourist.get().getTel());
            searchDto.setAddr(tourist.get().getAddr1());
            searchDto.setFirstImage(tourist.get().getFirstimage());
            searchDto.setType(1);

            results.add(searchDto);
        }

        if (careTripe.isPresent()) {
            SearchDto searchDto = modelMapper.map(careTripe.get(), SearchDto.class);
            searchDto.setType(2);

            results.add(searchDto);
        }

        if (charger.isPresent()) {
            SearchDto searchDto = modelMapper.map(charger.get(), SearchDto.class);
            searchDto.setType(3);

            results.add(searchDto);
        }

        if (rental.isPresent()) {
            SearchDto searchDto = modelMapper.map(rental.get(), SearchDto.class);
            searchDto.setType(4);

            results.add(searchDto);
        }

        return results;
    }

}
