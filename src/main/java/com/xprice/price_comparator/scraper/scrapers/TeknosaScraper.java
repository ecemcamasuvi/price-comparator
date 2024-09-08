package com.xprice.price_comparator.scraper.scrapers;

import com.xprice.price_comparator.model.dto.ScraperDto;
import com.xprice.price_comparator.model.document.ProductPrice;
import com.xprice.price_comparator.model.document.SiteDetails;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Element;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class TeknosaScraper implements Scraper{
    private final SiteDetails siteDetails;

    @Override
    public ProductPrice performScraping() {
        ScraperDto scraperDto = new ScraperDto(siteDetails);
        return scrapePrice(scraperDto);
    }

    @Override
    public String findProductName(Element element, String path) {
        return element.attr(path);
    }

    @Override
    public String findRelativeUrl(Element element, String path) {
        Element urlElement = element.select(path).first();

        if (urlElement == null) {
            throw new RuntimeException("Site URL could not be extracted.");
        }

        return urlElement != null ? urlElement.attr("href") : "";
    }

    @Override
    public BigDecimal findPrice(Element element, String path) {
        String priceText = element.attr(path);
        if (priceText == null || priceText.isEmpty()) {
            throw new RuntimeException("Price could not be extracted from the given path: " + path);
        }

        priceText = priceText.replace(" TL", "").replace(",", ".").trim();
        try {
            BigDecimal price = new BigDecimal(priceText);
            return price;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Failed to parse price: " + priceText, e);
        }

    }

}
