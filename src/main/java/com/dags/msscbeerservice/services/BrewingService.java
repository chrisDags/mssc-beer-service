package com.dags.msscbeerservice.services;

import com.dags.msscbeerservice.config.JmsConfig;
import com.dags.msscbeerservice.domain.Beer;
import com.dags.msscbeerservice.events.BrewBeerEvent;
import com.dags.msscbeerservice.repositories.BeerRepository;
import com.dags.msscbeerservice.services.inventory.BeerInventoryService;
import com.dags.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate JmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory(){
        List<Beer> beers = beerRepository.findAll();

        beers.forEach( beer ->{
            // makes a REST call to beer-inventory-service
            Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());

            log.debug("Min on hand is: " + beer.getMinOnHand());
            log.debug("Inventory is: " +  invQOH);

            if(beer.getMinOnHand() >= invQOH){
                JmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
