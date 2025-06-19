package com.whatsthatsitcom.repository;

import com.whatsthatsitcom.model.EpisodeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EpisodeCommentRepository extends JpaRepository<EpisodeComment, Long> {
    List<EpisodeComment> findByEpisodeId(Long episodeId);
}
