package com.xprice.price_comparator.model.repository;

import com.xprice.price_comparator.model.document.SiteDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SiteDetailsRepository extends MongoRepository<SiteDetails,Long> {
    Optional<SiteDetails> findByName(String name);
}
