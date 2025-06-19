package com.whatsthatsitcom.controller;

import com.whatsthatsitcom.model.User;
import com.whatsthatsitcom.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users") // Prefix for all endpoints
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // POST /api/users/register
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    // GET /api/users/profile/{id}
    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET /api/users/{id}/watchlist
    @GetMapping("/{id}/watchlist")
    public ResponseEntity<List<Long>> getWatchlist(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(u -> ResponseEntity.ok(u.getWatchlistSitcomIds()))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/users/{id}/watchlist
    @PostMapping("/{id}/watchlist")
    public ResponseEntity<?> addToWatchlist(@PathVariable Long id, @RequestBody Long sitcomId) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOpt.get();
        List<Long> watchlist = user.getWatchlistSitcomIds();
        if (!watchlist.contains(sitcomId)) {
            watchlist.add(sitcomId);
            userService.saveUser(user);
        }
        return ResponseEntity.ok(watchlist);
    }

    // DELETE /api/users/{id}/watchlist/{sitcomId}
    @DeleteMapping("/{id}/watchlist/{sitcomId}")
    public ResponseEntity<?> removeFromWatchlist(@PathVariable Long id, @PathVariable Long sitcomId) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isEmpty()) return ResponseEntity.notFound().build();

        User user = userOpt.get();
        List<Long> watchlist = user.getWatchlistSitcomIds();
        watchlist.remove(sitcomId);
        userService.saveUser(user);
        return ResponseEntity.ok(watchlist);
    }
}
