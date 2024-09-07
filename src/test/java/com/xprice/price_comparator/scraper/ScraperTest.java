package com.xprice.price_comparator.scraper;
import static org.junit.jupiter.api.Assertions.*;

import com.xprice.price_comparator.model.dto.ScraperDto;
import com.xprice.price_comparator.model.entity.ProductPrice;
import com.xprice.price_comparator.scraper.scrapers.TeknosaScraper;
import com.xprice.price_comparator.service.PriceCollectionService;
import com.xprice.price_comparator.service.impl.PriceCollectionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ScraperTest {
    @InjectMocks
    private TeknosaScraper scraper;

    @InjectMocks
    private PriceCollectionServiceImpl priceCollectionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testScrapePrice() throws Exception {
        ScraperDto scraperDto = new ScraperDto(
                "Teknosa",
                "https://www.teknosa.com",
                "https://www.teknosa.com/arama?s=MacBook+Air+M2+%3Aprice-asc%3Acategory%3A116%3Acategory%3A116004%3Aislemci_116-CLS-2500%3AApple%2BM2%2B%2B&text=MacBook+Air+M2+",
                "ul.non-style > li > div#product-item",
                "data-product-name",
                "data-price-with-discount",
                "a.prd-link"
        );

        ProductPrice result = scraper.scrapePrice(scraperDto);

        assertNotNull(result);
        assertEquals(new BigDecimal("41999.00").setScale(2, RoundingMode.HALF_UP), result.getPrice().setScale(2, RoundingMode.HALF_UP));
        assertEquals("https://www.teknosa.com/apple-macbook-air-m2-8c-cpu-8c-gpu-256gb-ssd-136-uzay-grisi-dizustu-bilgisayar-mlxw3tua-p-125035423", result.getProductUrl());
        assertNotNull(result.getLogTime());
    }

    @Test
    void testScrapePriceToSeeProductNotFoundError() throws Exception {
        ScraperDto scraperDto = new ScraperDto(
                "Teknosa",
                "https://www.teknosa.com",
                "https://www.teknosa.com/arama/?s=air+pods",
                "ul.non-style > li > div#product-item",
                "data-product-name",
                "data-price-with-discount",
                "a.prd-link"
        );

        Exception exception = assertThrows(RuntimeException.class, () -> {
            ProductPrice result = scraper.scrapePrice(scraperDto);
        });

        assertEquals("Error during scraping: No products found on " + scraperDto.getBaseUrl(), exception.getMessage());
    }

}
