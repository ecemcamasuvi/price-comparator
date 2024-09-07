package com.xprice.price_comparator.model.service.impl;

import com.xprice.price_comparator.model.entity.SiteDetails;
import com.xprice.price_comparator.model.service.SiteDetailsService;
import com.xprice.price_comparator.model.repository.SiteDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "siteDetailsService")
public class SiteDetailsServiceImpl implements SiteDetailsService {
    @Autowired
    private SiteDetailsRepository siteDetailsRepository;

    @Override
    public SiteDetails findByName(String name) {
        return siteDetailsRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("Site not found with name: " + name));
    }

    @Override
    public List<SiteDetails> findAll() {
        return siteDetailsRepository.findAll();
    }
}
