package com.artbridge.artist.service;

import com.artbridge.artist.service.dto.ArtistDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.artbridge.artist.domain.Artist}.
 */
public interface ArtistService {
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
     * Delete the "id" artist.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    ArtistDTO deletePending(ArtistDTO artistDTO);
}
