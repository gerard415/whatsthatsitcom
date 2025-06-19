package com.whatsthatsitcom.controller;

import com.whatsthatsitcom.model.EpisodeComment;
import com.whatsthatsitcom.model.CommunityPostComment;
import com.whatsthatsitcom.repository.EpisodeCommentRepository;
import com.whatsthatsitcom.repository.CommunityPostCommentRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserCommentController {

    private final EpisodeCommentRepository episodeCommentRepo;
    private final CommunityPostCommentRepository communityCommentRepo;

    public UserCommentController(
        EpisodeCommentRepository episodeCommentRepo,
        CommunityPostCommentRepository communityCommentRepo
    ) {
        this.episodeCommentRepo = episodeCommentRepo;
        this.communityCommentRepo = communityCommentRepo;
    }

    @GetMapping("/{userId}/episode-comments")
    public ResponseEntity<List<EpisodeComment>> getUserEpisodeComments(@PathVariable Long userId) {
        return ResponseEntity.ok(episodeCommentRepo.findByUserId(userId));
    }

    @GetMapping("/{userId}/community-comments")
    public ResponseEntity<List<CommunityPostComment>> getUserCommunityComments(@PathVariable Long userId) {
        return ResponseEntity.ok(communityCommentRepo.findByUserId(userId));
    }
}
