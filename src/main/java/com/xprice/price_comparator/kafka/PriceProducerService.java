package com.xprice.price_comparator.kafka;

import com.xprice.price_comparator.model.document.ProductPrice;
import com.xprice.price_comparator.model.enums.SiteEnum;
import com.xprice.price_comparator.service.PriceCollectionService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class PriceProducerService {
    private static final Logger logger = getLogger(PriceProducerService.class);
    private static final DecimalFormat df = new DecimalFormat("#0.00");

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

    /**
     * This method collecting the ProductPrice data for each webpage defined in SiteDetails
     */
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
        return formatPrice(productPriceList);
    }

    private List<ProductPrice> formatPrice(List<ProductPrice> productPriceList){
        productPriceList.forEach(productPrice -> {
            try {
                BigDecimal formattedPrice = new BigDecimal(df.format(productPrice.getPrice()));
                productPrice.setPrice(formattedPrice);
            } catch (NumberFormatException e) {
                logger.error("Error formatting price for product: " + productPrice, e);
            }
        });
        return productPriceList;
    }
}
