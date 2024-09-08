package com.xprice.price_comparator.kafka;

import com.xprice.price_comparator.kafka.PriceConsumerService;
import com.xprice.price_comparator.model.document.ProductPrice;
import com.xprice.price_comparator.model.service.ProductPriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(MockitoExtension.class)
public class KafkaTest {
    @Mock
    private KafkaTemplate<String, ProductPrice> kafkaTemplate;
    @Mock
    private ProductPriceService productPriceService;

    @InjectMocks
    private PriceConsumerService priceConsumerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

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

    @Test
    public void testFetchAndSendToKafka_whenSendFails_shouldHandleException() {
        doThrow(new RuntimeException("Kafka error")).when(kafkaTemplate).send(anyString(), any(ProductPrice.class));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            kafkaTemplate.send("prices",new ProductPrice());
        });

        assertEquals("Kafka error", exception.getMessage());
    }

    @Test
    public void testConsumerReceivesMessage() {
        ProductPrice productPrice = ProductPrice.builder()
                .siteName("Example")
                .productUrl("https://example.com")
                .price(BigDecimal.valueOf(99.99))
                .logTime(LocalDateTime.now())
                .build();

        priceConsumerService.consume(productPrice);

        verify(productPriceService, times(1)).save(productPrice);
    }

}
