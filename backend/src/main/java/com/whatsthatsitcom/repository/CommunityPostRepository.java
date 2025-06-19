package com.whatsthatsitcom.repository;

import com.whatsthatsitcom.model.CommunityPost;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostRepository extends JpaRepository<CommunityPost, Long> {
    List<CommunityPost> findByUserId(Long userId);
}
