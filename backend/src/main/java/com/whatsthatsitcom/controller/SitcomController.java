package com.whatsthatsitcom.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/search")
    public ResponseEntity<?> searchSitcoms(@RequestParam String q) {
        return ResponseEntity.ok(sitcomService.searchSitcoms(q));
    }
    
}
