package com.whatsthatsitcom.controller;

import com.whatsthatsitcom.model.CommunityPost;
import com.whatsthatsitcom.repository.CommunityPostRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserPostController {

    private final CommunityPostRepository postRepo;

    public UserPostController(CommunityPostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<CommunityPost>> getUserPosts(@PathVariable Long userId) {
        return ResponseEntity.ok(postRepo.findByUserId(userId));
    }
}
