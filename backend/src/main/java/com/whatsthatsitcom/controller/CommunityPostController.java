package com.whatsthatsitcom.controller;

import com.whatsthatsitcom.model.CommunityPost;
import com.whatsthatsitcom.service.CommunityPostService;
import com.whatsthatsitcom.exception.UnauthorizedException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/community/posts")
public class CommunityPostController {

    private final CommunityPostService postService;

    public CommunityPostController(CommunityPostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<CommunityPost>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PostMapping
    public ResponseEntity<CommunityPost> createPost(@RequestBody Map<String, String> body) {
        Long userId = Long.parseLong(body.get("userId"));
        String title = body.get("title");
        String content = body.get("content");

        CommunityPost post = postService.createPost(userId, title, content);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId, @RequestParam Long userId) {
        try {
            boolean deleted = postService.deletePost(postId, userId);
            return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
