package com.artbridge.artist.service;

import com.artbridge.artist.service.dto.CommentDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.artbridge.artist.domain.Comment}.
 */
public interface CommentService {
    /**
     * 주어진 CommentDTO를 사용하여 댓글을 저장합니다.
     *
     * @param commentDTO 저장할 댓글의 정보 (CommentDTO)
     * @return CommentDTO : 저장된 댓글을 반환합니다.
     */
    CommentDTO save(CommentDTO commentDTO);

    /**
     * Updates a comment.
     *
     * @param commentDTO the entity to update.
     * @return the persisted entity.
     */
    CommentDTO update(CommentDTO commentDTO);

    /**
     * Partially updates a comment.
     *
     * @param commentDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CommentDTO> partialUpdate(CommentDTO commentDTO);

    /**
     * Get all the comments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CommentDTO> findAll(Pageable pageable);

    /**
     * Get the "id" comment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CommentDTO> findOne(Long id);

    /**
     * Delete the "id" comment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * 특정 아티스트의 댓글 목록을 페이지로 가져옵니다.
     *
     * @param pageable  페이지 설정 (Pageable)
     * @param artistId  아티스트의 ID (Long)
     * @return Page<CommentDTO> : 페이지에 해당하는 댓글 목록을 반환합니다.
     */
    Page<CommentDTO> findByArtistId(Pageable pageable, Long artistId);
}
