package com.xprice.price_comparator.service;

import com.xprice.price_comparator.model.document.ProductPrice;
import com.xprice.price_comparator.model.enums.SiteEnum;

public interface PriceCollectionService {
    ProductPrice fetchPrice(SiteEnum siteEnum);
}
