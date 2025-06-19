package com.whatsthatsitcom.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SitcomService {

    private final RestTemplate restTemplate;

    public SitcomService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Object getAllSitcoms() {
        String url = "https://api.tvmaze.com/shows?page=0";
        Object[] allShows = restTemplate.getForObject(url, Object[].class);

        if (allShows == null || allShows.length == 0) {
            return List.of();
        }

        return Arrays.stream(allShows)
            .filter(showObj -> {
                if (showObj instanceof Map<?, ?> showMap) {
                    Object genresObj = showMap.get("genres");
                    if (genresObj instanceof List<?> genres) {
                        return genres.contains("Comedy"); // optionally check for "Family" too
                    }
                }
                return false;
            })
            .collect(Collectors.toList());
    }

    public Object searchSitcoms(String query) {
        String url = "https://api.tvmaze.com/search/shows?q=" + query;
        Object[] results = restTemplate.getForObject(url, Object[].class);

        if (results == null) return List.of();

        return Arrays.stream(results)
            .map(result -> ((Map<?, ?>) result).get("show"))
            .filter(showObj -> {
                if (showObj instanceof Map<?, ?> showMap) {
                    Object genresObj = showMap.get("genres");
                    if (genresObj instanceof List<?> genres) {
                        return genres.contains("Comedy");
                    }
                }
                return false;
            })
            .collect(Collectors.toList());
    }

    public Object getSitcomById(Long id) {
        String url = "https://api.tvmaze.com/shows/" + id;
        return restTemplate.getForObject(url, Object.class);
    }

    public Object getEpisodesForSitcom(Long id) {
        String url = "https://api.tvmaze.com/shows/" + id + "/episodes";
        return restTemplate.getForObject(url, Object.class);
    }

    public Object getEpisodeById(Long id) {
        String url = "https://api.tvmaze.com/episodes/" + id;
        return restTemplate.getForObject(url, Object.class);
    }

}

