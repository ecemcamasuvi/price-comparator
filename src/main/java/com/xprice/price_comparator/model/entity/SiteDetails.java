package com.xprice.price_comparator.model.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "siteDetails")
@Data
public class SiteDetails {
    @Id
    private ObjectId id;
    private String name;
    private String baseUrl;
    private String searchUrl;
    private String elementPath;
    private String productNamePathOfElement;
    private String pricePathOfElement;
    private String urlPathOfElement;
}
