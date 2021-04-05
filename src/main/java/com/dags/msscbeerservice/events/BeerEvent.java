package com.dags.msscbeerservice.events;

import com.dags.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {
    private final long serialVersionUID = 4745507246828224167L;
    private final BeerDto beerDto;
}
