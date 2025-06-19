package com.whatsthatsitcom.controller;

import com.whatsthatsitcom.model.CommunityPostComment;
import com.whatsthatsitcom.service.CommunityPostCommentService;
import com.whatsthatsitcom.exception.UnauthorizedException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/community/comments")
public class CommunityPostCommentController {

    private final CommunityPostCommentService commentService;

    public CommunityPostCommentController(CommunityPostCommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommunityPostComment>> getComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getComments(postId));
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommunityPostComment> addComment(@PathVariable Long postId, @RequestBody Map<String, String> body) {
        Long userId = Long.parseLong(body.get("userId"));
        String content = body.get("content");
        return ResponseEntity.ok(commentService.addComment(postId, userId, content));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId, @RequestParam Long userId) {
        try {
            boolean deleted = commentService.deleteComment(commentId, userId);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
