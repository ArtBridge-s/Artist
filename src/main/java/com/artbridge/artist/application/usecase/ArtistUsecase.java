package com.artbridge.artist.application.usecase;

import com.artbridge.artist.domain.model.Artist;
import com.artbridge.artist.application.dto.ArtistDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Artist}.
 */
public interface ArtistUsecase {
    /**
     * 아티스트 정보를 저장합니다.
     *
     * @param artistDTO 저장할 아티스트 정보 (ArtistDTO)
     * @return 저장된 아티스트의 정보를 담은 ArtistDTO 객체
     */
    ArtistDTO save(ArtistDTO artistDTO);

    /**
     * 아티스트 정보를 업데이트합니다.
     *
     * @param artistDTO 업데이트할 아티스트 정보 (ArtistDTO)
     * @return 업데이트된 아티스트의 정보를 담은 ArtistDTO 객체
     */
    ArtistDTO update(ArtistDTO artistDTO);

    /**
     * Partially updates a artist.
     *
     * @param artistDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ArtistDTO> partialUpdate(ArtistDTO artistDTO);

    /**
     * Get all the artists.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ArtistDTO> findAll(Pageable pageable);

    /**
     * 특정 아티스트 정보를 조회합니다.
     *
     * @param id 조회할 아티스트의 ID (Long)
     * @return 조회된 아티스트의 정보를 담은 Optional<ArtistDTO> 객체
     */
    Optional<ArtistDTO> findOne(Long id);

    /**
     * 아티스트를 삭제합니다.
     *
     * @param id 삭제할 아티스트의 ID (Long)
     */
    void delete(Long id);

    /**
     * 아티스트 정보를 "삭제 대기 중"으로 변경합니다.
     *
     * @param artistDTO 삭제 대기 중으로 변경할 아티스트 정보 (ArtistDTO)
     * @return 삭제 대기 중으로 변경된 아티스트의 정보를 담은 ArtistDTO 객체
     */
    ArtistDTO deletePending(ArtistDTO artistDTO);


    /**
     * 특정 상태에 해당하는 모든 아티스트 정보를 페이지별로 조회합니다.
     *
     * @param pageable 페이지 정보 (Pageable)
     * @return 페이지별로 조회된 아티스트 정보를 담은 Page 객체
     */
    Page<ArtistDTO> findAllByStatus(Pageable pageable);


    /**
     * 생성 대기 중인 아티스트 정보를 페이지별로 조회합니다.
     *
     * @param pageable 페이지 정보 (Pageable)
     * @return 페이지별로 조회된 아티스트 정보를 담은 Page 객체
     */
    Page<ArtistDTO> findCreatePendings(Pageable pageable);


    /**
     * 업데이트 대기 중인 아티스트 정보를 페이지별로 조회합니다.
     *
     * @param pageable 페이지 정보 (Pageable)
     * @return 페이지별로 조회된 아티스트 정보를 담은 Page 객체
     */
    Page<ArtistDTO> findUpdatePendings(Pageable pageable);


    /**
     * 삭제 대기 중인 아티스트 정보를 페이지별로 조회합니다.
     *
     * @param pageable 페이지 정보 (Pageable)
     * @return 페이지별로 조회된 아티스트 정보를 담은 Page 객체
     */
    Page<ArtistDTO> findDeletePendings(Pageable pageable);

    /**
     * 승인된 아티스트로 변경합니다.
     *
     * @param id 아티스트 ID
     * @return 변경된 아티스트 정보 (ArtistDTO)
     */
    ArtistDTO authorizeOkArtist(Long id);
}
