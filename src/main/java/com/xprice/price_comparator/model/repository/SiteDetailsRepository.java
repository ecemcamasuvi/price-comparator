package com.xprice.price_comparator.model.repository;

import com.xprice.price_comparator.model.entity.SiteDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface SiteDetailsRepository extends MongoRepository<SiteDetails,Long> {
    Optional<SiteDetails> findByName(String name);
}
