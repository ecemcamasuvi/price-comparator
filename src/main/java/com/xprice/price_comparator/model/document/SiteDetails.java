package com.xprice.price_comparator.model.document;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "siteDetails")
@Data
public class SiteDetails {
    @Id
    private ObjectId id;
    @Indexed
    private String name;
    private String baseUrl;
    private String searchUrl;
    private String elementPath;
    private String productNamePathOfElement;
    private String pricePathOfElement;
    private String urlPathOfElement;
}
