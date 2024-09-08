package com.xprice.price_comparator.model.repository;

import com.xprice.price_comparator.model.document.ProductPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceRepository extends MongoRepository<ProductPrice,Long> {
    @Query(value = "{ 'siteName': ?0 }", sort = "{ 'logTime': -1 }")
    Optional<ProductPrice> findLatestProductOfUrl(String siteName);
}
