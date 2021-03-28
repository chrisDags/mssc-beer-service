package com.dags.msscbeerservice.web.mappers;

import com.dags.msscbeerservice.domain.Beer;
import com.dags.msscbeerservice.services.inventory.BeerInventoryService;
import com.dags.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper {
    private BeerInventoryService beerInventoryService;
    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
//        System.out.println("has been called");
        return mapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer){
        BeerDto beerDto = mapper.beerToBeerDto(beer);
        beerDto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
        return beerDto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return mapper.beerDtoToBeer(beerDto);
    }
}