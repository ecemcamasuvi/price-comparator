package com.xprice.price_comparator.model.service;

import com.xprice.price_comparator.model.entity.ProductPrice;
import com.xprice.price_comparator.rmi.BackendService;

@BackendService("productPriceService")
public interface ProductPriceService {
    ProductPrice save(ProductPrice productPrice);
    ProductPrice getLatestProductPriceFromCache(String siteName);
    ProductPrice updateCache(ProductPrice productPrice);
}
