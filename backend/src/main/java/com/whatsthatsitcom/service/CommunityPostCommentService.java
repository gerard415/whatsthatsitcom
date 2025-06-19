package com.whatsthatsitcom.service;

import com.whatsthatsitcom.exception.UnauthorizedException;
import com.whatsthatsitcom.model.CommunityPost;
import com.whatsthatsitcom.model.CommunityPostComment;
import com.whatsthatsitcom.model.User;
import com.whatsthatsitcom.repository.CommunityPostCommentRepository;
import com.whatsthatsitcom.repository.CommunityPostRepository;
import com.whatsthatsitcom.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityPostCommentService {

    private final CommunityPostCommentRepository commentRepo;
    private final CommunityPostRepository postRepo;
    private final UserRepository userRepo;

    public CommunityPostCommentService(
        CommunityPostCommentRepository commentRepo,
        CommunityPostRepository postRepo,
        UserRepository userRepo
    ) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.userRepo = userRepo;
    }

    public List<CommunityPostComment> getComments(Long postId) {
        return commentRepo.findByPostId(postId);
    }

    public CommunityPostComment addComment(Long postId, Long userId, String content) {
        CommunityPost post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        CommunityPostComment comment = new CommunityPostComment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(content);

        return commentRepo.save(comment);
    }

    public boolean deleteComment(Long commentId, Long userId) {
        Optional<CommunityPostComment> commentOpt = commentRepo.findById(commentId);
        if (commentOpt.isEmpty()) return false;

        CommunityPostComment comment = commentOpt.get();
        if (!comment.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You cannot delete this comment.");
        }

        commentRepo.delete(comment);
        return true;
    }
}
