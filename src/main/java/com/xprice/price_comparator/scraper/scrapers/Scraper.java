package com.xprice.price_comparator.scraper.scrapers;

import com.xprice.price_comparator.model.dto.ScraperDto;
import com.xprice.price_comparator.model.entity.ProductPrice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface Scraper {
    ProductPrice performScraping();

    default ProductPrice scrapePrice(ScraperDto scraperDto){
        try{
            Document doc = Jsoup.connect(scraperDto.getSearchUrl())
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                    .get();
            Elements elements = doc.select(scraperDto.getElementPath());
            if (elements.isEmpty()) {
                throw new RuntimeException("Element not found for the given path: " + scraperDto.getElementPath());
            }

            Element element = elements.first();

            if (element == null) {
                throw new RuntimeException("No valid element found at the specified path.");
            }

            String productName = element.attr(scraperDto.getProductNamePathOfElement());
            BigDecimal price = null;
            if (productName.contains("MacBook Air M2")) {
                String priceText = element.attr(scraperDto.getPricePathOfElement());
                if (priceText == null || priceText.isEmpty()) {
                    throw new RuntimeException("Price could not be extracted from the given path: " + scraperDto.getPricePathOfElement());
                }

                priceText = priceText.replace(" TL", "").replace(",", ".").trim();
                try {
                    price = new BigDecimal(priceText);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Failed to parse price: " + priceText, e);
                }


            Element urlElement = element.select(scraperDto.getUrlPathOfElement()).first();

            if (urlElement == null) {
                throw new RuntimeException("Site URL could not be extracted.");
            }

            String relativeUrl = urlElement != null ? urlElement.attr("href") : "";
            String fullUrl = scraperDto.getBaseUrl() + relativeUrl;

            return ProductPrice.builder()
                    .price(price)
                    .siteName(scraperDto.getName())
                    .productUrl(fullUrl)
                    .logTime(LocalDateTime.now())
                    .build();
            }
            else{
                throw new RuntimeException("No products found on " + scraperDto.getBaseUrl());
            }

        }
        catch (IOException e) {
            throw new RuntimeException("Failed to connect to the URL: " + scraperDto.getSearchUrl() + ". " + e.getMessage(), e);
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Error during scraping: " + e.getMessage(), e);
        }
    }
}
