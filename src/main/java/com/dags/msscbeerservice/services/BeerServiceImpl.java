package com.dags.msscbeerservice.services;

import com.dags.msscbeerservice.domain.Beer;
import com.dags.msscbeerservice.repositories.BeerRepository;
import com.dags.msscbeerservice.web.controller.NotFoundException;
import com.dags.msscbeerservice.web.mappers.BeerMapper;
import com.dags.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId)
                .orElseThrow(() -> new NotFoundException("Beer of ID '" + beerId +" not found.")));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {

        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId)
                .orElseThrow(() -> new NotFoundException("Beer of ID '" + beerId + " not found."));

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}
