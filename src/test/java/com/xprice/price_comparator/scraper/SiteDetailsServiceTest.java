package com.xprice.price_comparator.scraper;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.xprice.price_comparator.model.entity.SiteDetails;
import com.xprice.price_comparator.model.repository.SiteDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class SiteDetailsServiceTest {
    @Mock
    private SiteDetailsRepository siteDetailsRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindByNameSiteNotFound() {
        String name = "Trendyol";
        when(siteDetailsRepository.findByName(name)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            siteDetailsRepository.findByName(name);
        });

        assertEquals("Site not found with name: " + name, exception.getMessage());
    }

    @Test
    void testSaveSiteDetails() {
        SiteDetails teknosa = new SiteDetails();
        teknosa.setName("Teknosa");
        teknosa.setBaseUrl("https://www.teknosa.com");
        teknosa.setSearchUrl("https://www.teknosa.com/arama?s=MacBook+Air+M2+%3Aprice-asc%3Acategory%3A116%3Acategory%3A116004%3Aislemci_116-CLS-2500%3AApple%2BM2%2B%2B&text=MacBook+Air+M2+");
        teknosa.setElementPath("ul.non-style > li > div#product-item");
        teknosa.setProductNamePathOfElement("data-product-name");
        teknosa.setPricePathOfElement("data-price-with-discount");
        teknosa.setUrlPathOfElement("a.prd-link");

        when(siteDetailsRepository.save(teknosa)).thenReturn(teknosa);

        SiteDetails savedSiteDetails = siteDetailsRepository.save(teknosa);

        assertNotNull(savedSiteDetails);
        assertEquals("Teknosa", savedSiteDetails.getName());
    }
}
