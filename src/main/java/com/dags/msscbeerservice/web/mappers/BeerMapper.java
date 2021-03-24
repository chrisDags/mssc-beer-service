package com.dags.msscbeerservice.web.mappers;
import com.dags.msscbeerservice.domain.Beer;
import com.dags.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;
@Mapper(uses = DateMapper.class)
public interface BeerMapper {
    BeerDto BeerToBeerDto(Beer beer);
    Beer BeerDtoToBeer(BeerDto beerDto);
}
