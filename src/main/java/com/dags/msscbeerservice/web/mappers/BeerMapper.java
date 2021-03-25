package com.dags.msscbeerservice.web.mappers;
import com.dags.msscbeerservice.domain.Beer;
import com.dags.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;
@Mapper(uses = DateMapper.class)
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
