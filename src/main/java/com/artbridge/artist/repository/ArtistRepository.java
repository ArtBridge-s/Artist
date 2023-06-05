package com.artbridge.artist.repository;

import com.artbridge.artist.domain.model.Artist;
import com.artbridge.artist.domain.standardType.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Artist entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Page<Artist> findAllByStatus(Pageable pageable, Status status);
}
