package com.xprice.price_comparator.scraper.factory;

import com.xprice.price_comparator.model.document.SiteDetails;
import com.xprice.price_comparator.model.enums.SiteEnum;
import com.xprice.price_comparator.model.service.SiteDetailsService;
import com.xprice.price_comparator.scraper.scrapers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScraperFactory {
    @Autowired
    private SiteDetailsService siteDetailsService;

    public Scraper getScraper(SiteEnum siteEnum){
        SiteDetails siteDetails = siteDetailsService.findByName(siteEnum.getName());
        return switch (siteEnum) {
            case TEKNOSA -> new TeknosaScraper(siteDetails);
            case PAZARAMA -> new PazaramaScraper(siteDetails);
            case MEDIAMARKT -> new MediamarktScraper(siteDetails);
            default -> throw new IllegalArgumentException("Unexpected site: " + siteEnum.getName());
        };
    }

}
