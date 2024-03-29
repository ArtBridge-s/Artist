package com.artbridge.artist.infrastructure.repository;

import com.artbridge.artist.domain.model.Artist;
import com.artbridge.artist.domain.standardType.Status;
import com.carrotsearch.hppc.ByteArrayList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Spring Data JPA repository for the Artist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Page<Artist> findAllByStatusOrderByIdDesc(Pageable pageable, Status status);

    Set<Artist> findAllByCreatedMemberId(Long memberId);
}
