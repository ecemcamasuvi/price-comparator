package com.xprice.price_comparator;

import com.xprice.price_comparator.model.document.ProductPrice;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PriceComparatorApplication {

	@Autowired
	private MongoTemplate mongoTemplate;
	public static void main(String[] args) {
		SpringApplication.run(PriceComparatorApplication.class, args);
	}

	@PostConstruct
	public void init() {
		if (!mongoTemplate.collectionExists(ProductPrice.class)) {
			mongoTemplate.createCollection(ProductPrice.class);
		}
	}

}
