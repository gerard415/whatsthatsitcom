package com.whatsthatsitcom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.whatsthatsitcom.service.SitcomService;

@RestController
@RequestMapping("/api/sitcoms")
public class SitcomController {

    private final SitcomService sitcomService;

    public SitcomController(SitcomService sitcomService) {
        this.sitcomService = sitcomService;
    }
    
    // GET /api/sitcoms
    @GetMapping
    public ResponseEntity<?> getAllSitcoms() {
        return ResponseEntity.ok(sitcomService.getAllSitcoms());
    }

    // GET /api/sitcoms/search?q=office
    @GetMapping("/search")
    public ResponseEntity<?> searchSitcoms(@RequestParam String q) {
        return ResponseEntity.ok(sitcomService.searchSitcoms(q));
    }

    // GET /api/sitcoms/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getSitcomById(@PathVariable Long id) {
        return ResponseEntity.ok(sitcomService.getSitcomById(id));
    }

    // GET /api/sitcoms/{id}/episodes
    @GetMapping("/{id}/episodes")
    public ResponseEntity<?> getEpisodesForSitcom(@PathVariable Long id) {
        return ResponseEntity.ok(sitcomService.getEpisodesForSitcom(id));
    }
    
}
