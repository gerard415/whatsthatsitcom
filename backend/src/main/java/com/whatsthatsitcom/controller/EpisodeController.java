package com.whatsthatsitcom.controller;

import com.whatsthatsitcom.service.SitcomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeController {

    private final SitcomService sitcomService;

    public EpisodeController(SitcomService sitcomService) {
        this.sitcomService = sitcomService;
    }

    // GET /api/episodes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getEpisodeById(@PathVariable Long id) {
        return ResponseEntity.ok(sitcomService.getEpisodeById(id));
    }
}
