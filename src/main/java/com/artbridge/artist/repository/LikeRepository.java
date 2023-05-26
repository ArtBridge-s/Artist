package com.artbridge.artist.repository;

import com.artbridge.artist.domain.Like;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Like entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    boolean existsByArtist_IdAndMember_Id(Long artworkId, Long memberId);

    void deleteByArtist_IdAndMember_Id(Long artistId, Long memberId);

}
