package com.xprice.price_comparator.kafka;

import com.xprice.price_comparator.model.entity.ProductPrice;
import com.xprice.price_comparator.model.service.ProductPriceService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class PriceConsumerService {
    private static final Logger logger = getLogger(PriceProducerService.class);

    @Autowired
    private ProductPriceService productPriceService;
    @KafkaListener(topics = "prices", groupId = "price_group")
    public void consume(ProductPrice productPrice){
       productPriceService.save(productPrice);
       logger.info("Consumed product price: {}", productPrice);
    }
}
