package com.xprice.price_comparator.model.service;

import com.xprice.price_comparator.model.document.SiteDetails;
import com.xprice.price_comparator.rmi.BackendService;

import java.util.List;

@BackendService("siteDetailsService")
public interface SiteDetailsService {
    public SiteDetails findByName(String name);
    List<SiteDetails> findAll();
}
