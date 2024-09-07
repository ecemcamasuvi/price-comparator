package com.xprice.price_comparator.service.impl;

import com.xprice.price_comparator.model.entity.ProductPrice;
import com.xprice.price_comparator.model.enums.SiteEnum;
import com.xprice.price_comparator.scraper.factory.ScraperFactory;
import com.xprice.price_comparator.scraper.scrapers.Scraper;
import com.xprice.price_comparator.service.PriceCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceCollectionServiceImpl implements PriceCollectionService {
    @Autowired
    private ScraperFactory scraperFactory;
    @Override
    public ProductPrice fetchPrice(SiteEnum siteEnum){
        Scraper scraper = scraperFactory.getScraper(siteEnum);
        ProductPrice productPrice = scraper.performScraping();
        return productPrice;
    }
}
