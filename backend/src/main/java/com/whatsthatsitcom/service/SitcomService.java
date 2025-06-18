package com.whatsthatsitcom.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SitcomService {

    private final RestTemplate restTemplate;

    public SitcomService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object searchSitcoms(String query) {
        String url = "https://api.tvmaze.com/search/shows?q=" + query;
        return restTemplate.getForObject(url, Object.class);
    }
}

