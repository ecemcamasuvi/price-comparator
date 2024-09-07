package com.xprice.price_comparator.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductPriceDto {
    private String productUrl;
    private BigDecimal price;
}
