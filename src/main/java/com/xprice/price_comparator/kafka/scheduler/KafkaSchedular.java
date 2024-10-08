package com.xprice.price_comparator.kafka.scheduler;

import com.xprice.price_comparator.kafka.PriceProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaSchedular {

    @Autowired
    private PriceProducerService priceProducerService;

    @Scheduled(fixedRateString = "${schedule.fixedRate}") //It defined as 1 hour
    public void fetchAndSendToKafka(){
        priceProducerService.fetchAndSendToKafka();
    }

}
