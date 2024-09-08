package com.xprice.price_comparator.comparator;
import com.xprice.price_comparator.model.document.ProductPrice;
import com.xprice.price_comparator.model.service.ProductPriceService;
import com.xprice.price_comparator.service.impl.PriceComparatorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class PriceComparatorServiceTest {

    @Mock
    private ProductPriceService productPriceService;

    @InjectMocks
    private PriceComparatorServiceImpl priceComparatorServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetBestPrice_whenNoPricesFound_shouldThrowError() throws Exception {
        when(productPriceService.getLatestProductPriceFromCache(anyString())).thenReturn(null);

        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            priceComparatorServiceImpl.getBestPrice();
        });

        assertEquals("No product prices found.", exception.getMessage());
    }

    @Test
    void testGetBestPrice_whenUnexpectedErrorOccurs_shouldThrowException() {
        when(productPriceService.getLatestProductPriceFromCache(anyString())).thenThrow(new RuntimeException("Unexpected error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            priceComparatorServiceImpl.getBestPrice();
        });

        assertEquals("Unexpected error", exception.getMessage());
    }

    @Test
    public void testFindBestPrice() throws Exception{
        ProductPrice mediamarkt = ProductPrice.builder()
                .siteName("Mediamarkt")
                .productUrl("https://www.mediamarkt.com.tr")
                .price(BigDecimal.valueOf(150))
                .logTime(LocalDateTime.of(2024, 9, 8, 12, 0))
                .build();

        ProductPrice teknosa = ProductPrice.builder()
                .siteName("Teknosa")
                .productUrl("https://www.teknosa.com")
                .price(BigDecimal.valueOf(120.000000008))
                .logTime(LocalDateTime.of(2024, 9, 8, 12, 30))
                .build();

        ProductPrice pazarama = ProductPrice.builder()
                .siteName("Pazarama")
                .productUrl("https://www.pazarama.com")
                .price(BigDecimal.valueOf(120.0000080))
                .logTime(LocalDateTime.of(2024, 9, 8, 13, 0))
                .build();

        List<ProductPrice> productPriceList = List.of(mediamarkt, teknosa, pazarama);

        PriceComparatorServiceImpl service = new PriceComparatorServiceImpl();

        Method method = PriceComparatorServiceImpl.class.getDeclaredMethod("findBestPrice", List.class);
        method.setAccessible(true);
        ProductPrice bestPrice = (ProductPrice) method.invoke(service, productPriceList);

        assertEquals(teknosa, bestPrice);
    }

}
