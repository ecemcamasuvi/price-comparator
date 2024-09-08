package com.xprice.price_comparator.model.service.impl;

import com.xprice.price_comparator.model.document.ProductPrice;
import com.xprice.price_comparator.model.service.ProductPriceService;
import com.xprice.price_comparator.model.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {
    @Autowired
    private ProductPriceRepository productPriceRepository;

    @Override
    public ProductPrice save(ProductPrice productPrice) {
        ProductPrice latestProduct = getLatestProductPriceFromCache(productPrice.getSiteName());
        if (latestProduct != null) {
            if (!latestProduct.getProductUrl().equals(productPrice.getProductUrl()) ||
                    !latestProduct.getPrice().equals(productPrice.getPrice())) {
                productPrice.setLogTime(LocalDateTime.now());
                productPriceRepository.save(productPrice);
                updateCache(productPrice);
            }
        } else {
            productPrice.setLogTime(LocalDateTime.now());
            productPriceRepository.save(productPrice);
            updateCache(productPrice);
        }

        return productPrice;
    }

    @Override
    @Cacheable(value = "productPrices", key = "#productPrice.siteName")
    public ProductPrice getLatestProductPriceFromCache(String siteName) {
        return productPriceRepository.findLatestProductOfUrl(siteName).orElse(null);
    }

    @Override
    @CachePut(value = "productPrices", key = "#productPrice.siteName")
    public ProductPrice updateCache(ProductPrice productPrice) {
        return productPrice;
    }
}
