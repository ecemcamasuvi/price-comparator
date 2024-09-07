package com.xprice.price_comparator.service;

import com.xprice.price_comparator.model.dto.ProductPriceDto;

public interface PriceComparatorService {
    ProductPriceDto getBestPrice();
}
