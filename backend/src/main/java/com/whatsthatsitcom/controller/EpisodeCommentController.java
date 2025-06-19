package com.whatsthatsitcom.controller;

import com.whatsthatsitcom.model.EpisodeComment;
import com.whatsthatsitcom.service.EpisodeCommentService;
import com.whatsthatsitcom.exception.UnauthorizedException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeCommentController {

    private final EpisodeCommentService commentService;

    public EpisodeCommentController(EpisodeCommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{episodeId}/comments")
    public ResponseEntity<List<EpisodeComment>> getComments(@PathVariable Long episodeId) {
        return ResponseEntity.ok(commentService.getCommentsForEpisode(episodeId));
    }

    @PostMapping("/{episodeId}/comments")
    public ResponseEntity<EpisodeComment> addComment(
            @PathVariable Long episodeId,
            @RequestBody Map<String, String> body
    ) {
        Long userId = Long.parseLong(body.get("userId"));
        String content = body.get("content");

        EpisodeComment comment = commentService.addComment(episodeId, userId, content);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long commentId,
            @RequestParam Long userId
    ) {
        try {
            boolean deleted = commentService.deleteComment(commentId, userId);
            if (deleted) {
                return ResponseEntity.noContent().build(); // 204
            } else {
                return ResponseEntity.notFound().build(); // 404
            }
        } catch (UnauthorizedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }
}
