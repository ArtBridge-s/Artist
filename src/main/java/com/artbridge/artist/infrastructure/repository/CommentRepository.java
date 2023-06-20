package com.artbridge.artist.infrastructure.repository;

import com.artbridge.artist.domain.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Spring Data JPA repository for the Comment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    boolean existsByArtist_Id(Long artistId);

    Page<Comment> findByArtist_Id(Pageable pageable, Long artistId);

    Set<Comment> findCommentsByMember_Id(long id);
}
