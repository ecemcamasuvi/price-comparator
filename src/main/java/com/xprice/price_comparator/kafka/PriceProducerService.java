package com.xprice.price_comparator.kafka;

import com.xprice.price_comparator.model.document.ProductPrice;
import com.xprice.price_comparator.model.enums.SiteEnum;
import com.xprice.price_comparator.service.PriceCollectionService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class PriceProducerService {
    private static final Logger logger = getLogger(PriceProducerService.class);

    @Autowired
    private KafkaTemplate<String, ProductPrice> kafkaTemplate;

    @Autowired
    private PriceCollectionService priceCollectionService;
    public void fetchAndSendToKafka() {
        List<ProductPrice> productPriceList = this.fetchProductPrice();
        for(ProductPrice productPrice: productPriceList){
            kafkaTemplate.send("prices",productPrice);
            logger.info("Sent product price: {}", productPrice);
        }
    }

    private List<ProductPrice> fetchProductPrice(){
        List<ProductPrice> productPriceList = new ArrayList<>();
        for(SiteEnum siteEnum:SiteEnum.values()){
            try{
                ProductPrice productPrice = priceCollectionService.fetchPrice(siteEnum);
                productPriceList.add(productPrice);
            }
            catch(Exception e){
                logger.error(LocalDateTime.now() +" - Error fetching price for: " + siteEnum, e);
            }
        }
        return productPriceList;
    }
}
