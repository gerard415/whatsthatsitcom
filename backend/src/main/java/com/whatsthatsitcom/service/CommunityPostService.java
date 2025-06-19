package com.whatsthatsitcom.service;

import com.whatsthatsitcom.model.CommunityPost;
import com.whatsthatsitcom.model.User;
import com.whatsthatsitcom.repository.CommunityPostRepository;
import com.whatsthatsitcom.repository.UserRepository;
import com.whatsthatsitcom.exception.UnauthorizedException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityPostService {

    private final CommunityPostRepository postRepository;
    private final UserRepository userRepository;

    public CommunityPostService(CommunityPostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<CommunityPost> getAllPosts() {
        return postRepository.findAll();
    }

    public CommunityPost createPost(Long userId, String title, String content) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        CommunityPost post = new CommunityPost();
        post.setUser(userOpt.get());
        post.setTitle(title);
        post.setContent(content);

        return postRepository.save(post);
    }

    public boolean deletePost(Long postId, Long userId) {
        Optional<CommunityPost> postOpt = postRepository.findById(postId);
        if (postOpt.isEmpty()) return false;

        CommunityPost post = postOpt.get();
        if (!post.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You cannot delete this post.");
        }

        postRepository.delete(post);
        return true;
    }
}
