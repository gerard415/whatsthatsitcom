package com.whatsthatsitcom.repository;

import com.whatsthatsitcom.model.CommunityPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommunityPostCommentRepository extends JpaRepository<CommunityPostComment, Long> {
    List<CommunityPostComment> findByPostId(Long postId);
    List<CommunityPostComment> findByUserId(Long userId);
}
