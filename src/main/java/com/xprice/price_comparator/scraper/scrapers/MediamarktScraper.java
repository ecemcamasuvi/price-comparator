package com.xprice.price_comparator.scraper.scrapers;

import com.xprice.price_comparator.model.dto.ScraperDto;
import com.xprice.price_comparator.model.entity.ProductPrice;
import com.xprice.price_comparator.model.entity.SiteDetails;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
public class MediamarktScraper implements Scraper{
    private final SiteDetails siteDetails;

    @Override
    public ProductPrice performScraping() {
        ScraperDto scraperDto = new ScraperDto(siteDetails);
        return scrapePrice(scraperDto);
    }

}
