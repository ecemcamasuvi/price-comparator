package com.xprice.price_comparator.scraper;
import static org.junit.jupiter.api.Assertions.*;

import com.xprice.price_comparator.model.dto.ScraperDto;
import com.xprice.price_comparator.model.entity.ProductPrice;
import com.xprice.price_comparator.scraper.scrapers.MediamarktScraper;
import com.xprice.price_comparator.scraper.scrapers.PazaramaScraper;
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
    private TeknosaScraper teknosaScraper;

    @InjectMocks
    private MediamarktScraper mediamarktScraper;

    @InjectMocks
    private PazaramaScraper pazaramaScraper;

    @InjectMocks
    private PriceCollectionServiceImpl priceCollectionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testTeknosaScrapePrice() throws Exception {
        ScraperDto scraperDto = new ScraperDto(
                "Teknosa",
                "https://www.teknosa.com",
                "https://www.teknosa.com/arama?s=MacBook+Air+M2+%3Aprice-asc%3Acategory%3A116%3Acategory%3A116004%3Aislemci_116-CLS-2500%3AApple%2BM2%2B%2B&text=MacBook+Air+M2+",
                "ul.non-style > li > div#product-item",
                "data-product-name",
                "data-price-with-discount",
                "a.prd-link"
        );

        ProductPrice result = teknosaScraper.scrapePrice(scraperDto);

        assertNotNull(result);
        assertEquals(new BigDecimal("41999.00").setScale(2, RoundingMode.HALF_UP), result.getPrice().setScale(2, RoundingMode.HALF_UP));
        assertEquals("https://www.teknosa.com/apple-macbook-air-m2-8c-cpu-8c-gpu-256gb-ssd-136-uzay-grisi-dizustu-bilgisayar-mlxw3tua-p-125035423", result.getProductUrl());
        assertNotNull(result.getLogTime());
    }

    @Test
    void testMediamarktScrapePrice() throws Exception {
        ScraperDto scraperDto = new ScraperDto(
                "Mediamarkt",
                "https://www.mediamarkt.com.tr",
                "https://www.mediamarkt.com.tr/tr/category/macbook-air-645070.html?filter=processorModel:M2&sort=currentprice+asc",
                "div[data-test=mms-product-card]",
                "p[data-test=product-title]",
                "span.sc-8b815c14-0.hIJQGE.sc-dd1a61d2-2.efAprc",
                "a[data-test=mms-product-list-item-link]"
        );

        ProductPrice result = mediamarktScraper.scrapePrice(scraperDto);

        assertNotNull(result);
        assertEquals(new BigDecimal("34999.00").setScale(2, RoundingMode.HALF_UP), result.getPrice().setScale(2, RoundingMode.HALF_UP));
        assertEquals("https://www.mediamarkt.com.tr/tr/product/_apple-macbook-air-m2-8gb-256gb-ssd-136inc-yildiz-isigi-mly13tua-1222683.html", result.getProductUrl());
        assertNotNull(result.getLogTime());
    }

    @Test
    void testPazaramaScrapePrice() throws Exception {
        ScraperDto scraperDto = new ScraperDto(
                "Pazarama",
                "https://www.pazarama.com",
                "https://www.pazarama.com/arama?kategori=dizustu-bilgisayar-k-K04078&q=MacBook%20Air%20M2&siralama=artan-fiyat",
                ".product-card.bg-white.relative",
                "a[title]",
                ".leading-tight.text-blue-500.font-semibold.text-huge",
                "a[href]"
        );

        ProductPrice result = pazaramaScraper.scrapePrice(scraperDto);

        assertNotNull(result);
        assertEquals(new BigDecimal("34271.04").setScale(2, RoundingMode.HALF_UP), result.getPrice().setScale(2, RoundingMode.HALF_UP));
        assertEquals("https://www.pazarama.com/apple-macbook-air-13-m2-cip-8-cekirdekli-cpu-8-cekirdekli-gpu-8-gb-bellek-256gb-ssd-gece-yarisi-mly33tu-a-p-194253083382", result.getProductUrl());
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
            ProductPrice result = teknosaScraper.scrapePrice(scraperDto);
        });

        assertEquals("Error during scraping: No products found on " + scraperDto.getBaseUrl(), exception.getMessage());
    }

}
