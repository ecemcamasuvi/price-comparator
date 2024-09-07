package com.xprice.price_comparator.scraper;

import com.xprice.price_comparator.kafka.PriceProducerService;
import com.xprice.price_comparator.model.entity.ProductPrice;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerTest {
    @InjectMocks
    private PriceProducerService priceProducerService;

    @Mock
    private KafkaTemplate<String, ProductPrice> kafkaTemplate;

    @Test
    public void testFetchAndSendToKafka() {
        ProductPrice productPrice = ProductPrice.builder()
                .siteName("Example")
                .productUrl("https://example.com")
                .price(BigDecimal.valueOf(99.99))
                .logTime(LocalDateTime.now())
                .build();

        kafkaTemplate.send("prices",productPrice);

        verify(kafkaTemplate, times(1)).send("prices", productPrice);
    }
}
