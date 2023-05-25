package com.artbridge.artist.service.impl;

import com.artbridge.artist.domain.Artist;
import com.artbridge.artist.domain.enumeration.Status;
import com.artbridge.artist.repository.ArtistRepository;
import com.artbridge.artist.service.ArtistService;
import com.artbridge.artist.service.dto.ArtistDTO;
import com.artbridge.artist.service.mapper.ArtistMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Artist}.
 */
@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

    private final Logger log = LoggerFactory.getLogger(ArtistServiceImpl.class);

    private final ArtistRepository artistRepository;

    private final ArtistMapper artistMapper;

    public ArtistServiceImpl(ArtistRepository artistRepository, ArtistMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    @Override
    public ArtistDTO save(ArtistDTO artistDTO) {
        log.debug("Request to save Artist : {}", artistDTO);
        Artist artist = artistMapper.toEntity(artistDTO);
        artist.setStatus(Status.UPLOAD_PENDING);
        artist = artistRepository.save(artist);
        return artistMapper.toDto(artist);
    }

    @Override
    public ArtistDTO update(ArtistDTO artistDTO) {
        log.debug("Request to update Artist : {}", artistDTO);
        Artist artist = artistMapper.toEntity(artistDTO);
        artist.setStatus(Status.REVISION_PENDING);
        artist = artistRepository.save(artist);
        return artistMapper.toDto(artist);
    }

    @Override
    public Optional<ArtistDTO> partialUpdate(ArtistDTO artistDTO) {
        log.debug("Request to partially update Artist : {}", artistDTO);

        return artistRepository
            .findById(artistDTO.getId())
            .map(existingArtist -> {
                artistMapper.partialUpdate(existingArtist, artistDTO);

                return existingArtist;
            })
            .map(artistRepository::save)
            .map(artistMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ArtistDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Artists");
        return artistRepository.findAll(pageable).map(artistMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArtistDTO> findOne(Long id) {
        log.debug("Request to get Artist : {}", id);
        return artistRepository.findById(id).map(artistMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Artist : {}", id);
        artistRepository.deleteById(id);
    }

    @Override
    public ArtistDTO deletePending(ArtistDTO artistDTO) {
        log.debug("Request to delete pending Artist : {}", artistDTO);
        Artist artist = artistMapper.toEntity(artistDTO);
        artist.setStatus(Status.DELETE_PENDING);
        artist = artistRepository.save(artist);
        return artistMapper.toDto(artist);
    }

    @Override
    public Page<ArtistDTO> findAllByStatus(Pageable pageable) {
        log.debug("Request to get all Artists by status");
        return artistRepository.findAllByStatus(pageable, Status.OK).map(artistMapper::toDto);
    }

    @Override
    public Page<ArtistDTO> findUpdatePendings(Pageable pageable) {
        log.debug("Request to get all Artists by status");
        return artistRepository.findAllByStatus(pageable, Status.REVISION_PENDING).map(artistMapper::toDto);
    }
}
