package com.xprice.price_comparator.model.dto;

import com.xprice.price_comparator.model.document.SiteDetails;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScraperDto {
    private String name;
    private String baseUrl;
    private String searchUrl;
    private String elementPath;
    private String productNamePathOfElement;
    private String pricePathOfElement;
    private String urlPathOfElement;

    public ScraperDto(SiteDetails siteDetails) {
        this.name = siteDetails.getName();
        this.baseUrl = siteDetails.getBaseUrl();
        this.searchUrl = siteDetails.getSearchUrl();
        this.elementPath = siteDetails.getElementPath();
        this.productNamePathOfElement = siteDetails.getProductNamePathOfElement();
        this.pricePathOfElement = siteDetails.getPricePathOfElement();
        this.urlPathOfElement = siteDetails.getUrlPathOfElement();
    }
}
