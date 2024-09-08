package com.xprice.price_comparator.model.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@CompoundIndex(name = "latest_product_idx", def = "{'siteName': 1, 'logTime': -1}")
public class ProductPrice {
    @Id
    private ObjectId id;
    private String siteName;
    private String productUrl;
    private BigDecimal price;
    private LocalDateTime logTime;

}
