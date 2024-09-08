package com.xprice.price_comparator.service.impl;

import com.xprice.price_comparator.model.dto.ProductPriceDto;
import com.xprice.price_comparator.model.document.ProductPrice;
import com.xprice.price_comparator.model.enums.SiteEnum;
import com.xprice.price_comparator.model.service.ProductPriceService;
import com.xprice.price_comparator.service.PriceComparatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class PriceComparatorServiceImpl implements PriceComparatorService {

    @Autowired
    private ProductPriceService productPriceService;
    @Override
    public ProductPriceDto getBestPrice() {
        List<ProductPrice> latestRecordsOfEachSite = findLatestRecordsOfEachSite();
        ProductPrice bestProductPrice = latestRecordsOfEachSite.stream().min(Comparator.comparing(ProductPrice::getPrice)).orElse(null);
        return bestProductPrice == null ? null : ProductPriceDto.builder()
                .productUrl(bestProductPrice.getProductUrl())
                .price(bestProductPrice.getPrice())
                .build();
    }

    private List<ProductPrice> findLatestRecordsOfEachSite(){
        List<ProductPrice> latestRecordsOfEachSite = new ArrayList<>();
        for(SiteEnum siteEnum:SiteEnum.values()){
            ProductPrice productPrice = productPriceService.getLatestProductPriceFromCache(siteEnum.getName());
            latestRecordsOfEachSite.add(productPrice);
        }
        return latestRecordsOfEachSite;
    }

}
