package com.artbridge.artist.service;

import com.artbridge.artist.service.dto.LikeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.artbridge.artist.domain.Like}.
 */
public interface LikeService {

    /**
     * 좋아요 정보를 저장합니다.
     *
     * @param likeDTO 저장할 좋아요 정보 (LikeDTO)
     * @return 저장된 좋아요 정보 (LikeDTO)
     */
    LikeDTO save(LikeDTO likeDTO);

    /**
     * Updates a like.
     *
     * @param likeDTO the entity to update.
     * @return the persisted entity.
     */
    LikeDTO update(LikeDTO likeDTO);

    /**
     * Partially updates a like.
     *
     * @param likeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LikeDTO> partialUpdate(LikeDTO likeDTO);

    /**
     * Get all the likes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LikeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" like.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LikeDTO> findOne(Long id);

    /**
     * Delete the "id" like.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    Long countByArtworkId(Long artworkId);

}
