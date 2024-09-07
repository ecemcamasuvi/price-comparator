package com.xprice.price_comparator.kafka.scheduler;

import com.xprice.price_comparator.kafka.PriceProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaSchedular {

    @Autowired
    private PriceProducerService priceProducerService;

    @Scheduled(fixedRate = 300000) //5 minutes
    public void fetchAndSendToKafka(){
        priceProducerService.fetchAndSendToKafka();
    }

}
