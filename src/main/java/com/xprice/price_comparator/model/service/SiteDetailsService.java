package com.xprice.price_comparator.model.service;

import com.xprice.price_comparator.model.entity.SiteDetails;
import com.xprice.price_comparator.rmi.BackendService;

import java.util.List;

@BackendService("siteDetailsService")
public interface SiteDetailsService {
    public SiteDetails findByName(String name);
    List<SiteDetails> findAll();
}
