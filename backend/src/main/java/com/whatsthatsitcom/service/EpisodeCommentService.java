package com.whatsthatsitcom.service;

import com.whatsthatsitcom.model.EpisodeComment;
import com.whatsthatsitcom.model.User;
import com.whatsthatsitcom.repository.EpisodeCommentRepository;
import com.whatsthatsitcom.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EpisodeCommentService {

    private final EpisodeCommentRepository commentRepository;
    private final UserRepository userRepository;

    public EpisodeCommentService(EpisodeCommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public List<EpisodeComment> getCommentsForEpisode(Long episodeId) {
        return commentRepository.findByEpisodeId(episodeId);
    }

    public EpisodeComment addComment(Long episodeId, Long userId, String content) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        EpisodeComment comment = new EpisodeComment();
        comment.setEpisodeId(episodeId);
        comment.setUser(userOpt.get());
        comment.setContent(content);

        return commentRepository.save(comment);
    }

    public boolean deleteComment(Long commentId, Long userId) {
    Optional<EpisodeComment> commentOpt = commentRepository.findById(commentId);

    if (commentOpt.isEmpty()) {
        return false;
    }

    EpisodeComment comment = commentOpt.get();
    if (!comment.getUser().getId().equals(userId)) {
        throw new RuntimeException("Unauthorized to delete this comment.");
    }

    commentRepository.delete(comment);
    return true;
}
}
