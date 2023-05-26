package com.artbridge.artist.repository;

import com.artbridge.artist.domain.Comment;
import com.hazelcast.jet.Traverser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Comment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsByArtist_Id(Long artistId);

    Page<Comment> findByArtist_Id(Pageable pageable, Long artistId);
}
