package com.xprice.price_comparator.model.entity;

import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Data
@Builder
public class ProductPrice {
    @Id
    private ObjectId id;
    private String siteName;
    private String productUrl;
    private BigDecimal price;
    private LocalDateTime logTime;

}
