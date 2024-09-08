package com.xprice.price_comparator.service.impl;

import com.xprice.price_comparator.model.dto.ProductPriceDto;
import com.xprice.price_comparator.model.document.ProductPrice;
import com.xprice.price_comparator.model.enums.SiteEnum;
import com.xprice.price_comparator.model.service.ProductPriceService;
import com.xprice.price_comparator.service.PriceComparatorService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class PriceComparatorServiceImpl implements PriceComparatorService {
    private static final Logger logger = getLogger(PriceComparatorServiceImpl.class);
    @Autowired
    private ProductPriceService productPriceService;

    /**
     * This method finds the latest record for each website, compares it and determines the best price.
     */
    @Override
    public ProductPriceDto getBestPrice() {
        try{
            List<ProductPrice> latestRecordsOfEachSite = findLatestRecordsOfEachSite();
        if (latestRecordsOfEachSite == null || latestRecordsOfEachSite.isEmpty()) {
            throw new NoSuchElementException("No product prices found.");
        }
        ProductPrice bestProductPrice = findBestPrice(latestRecordsOfEachSite);
        return ProductPriceDto.builder()
                .productUrl(bestProductPrice.getProductUrl())
                .price(bestProductPrice.getPrice())
                .build();
        } catch (NoSuchElementException e) {
            logger.error("Price calculation failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: " + e.getMessage(), e);
            throw e;
        }
    }

    private List<ProductPrice> findLatestRecordsOfEachSite(){
        List<ProductPrice> latestRecordsOfEachSite = new ArrayList<>();
        for(SiteEnum siteEnum:SiteEnum.values()){
            ProductPrice productPrice = productPriceService.getLatestProductPriceFromCache(siteEnum.getName());
            if(productPrice != null){
                latestRecordsOfEachSite.add(productPrice);
            }
        }
        return latestRecordsOfEachSite;
    }

    private ProductPrice findBestPrice(List<ProductPrice> productPriceList){
        return productPriceList
                .stream()
                .min(Comparator.comparing(ProductPrice::getPrice))
                .orElseThrow(() -> new NoSuchElementException("No valid price found."));
    }

}
